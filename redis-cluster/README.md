```shell
docker compose build --build-arg "redis_version=7.0.5" redis-cluster
docker compose up -d
```

---

以下メモ

https://redis.io/docs/manual/scaling/

Redis Clusterの準備

```shell
docker network create redis-cluster-network
docker compose build
```

```shell
# ポートが被る場合があるので成功するまで繰り返す
# Cluster化には最低6必要
docker compose up -d --scale redis=6 
```

各RedisのIPアドレスを取得

```shell
docker network inspect redis-cluster_redis-cluster-network | jq '.[0].Containers | .[].IPv4Address'
"192.168.144.2/20"
"192.168.144.6/20"
"192.168.144.5/20"
"192.168.144.3/20"
"192.168.144.4/20"
"192.168.144.7/20"
```

Clusterの初期化

```shell
docker compose exec redis redis-cli --cluster create 192.168.144.2:6379 192.168.144.3:6379 \
192.168.144.4:6379 192.168.144.5:6379 192.168.144.6:6379 192.168.144.7:6379 \
--cluster-replicas 1

docker compose exec redis redis-cli --cluster create 192.168.240.2:6379 192.168.240.3:6379 \
192.168.240.4:6379 192.168.240.5:6379 192.168.240.6:6379 192.168.240.7:6379 \
--cluster-replicas 1
```

```shell
>>> Performing hash slots allocation on 6 nodes...
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
Adding replica 192.168.144.6:6379 to 192.168.144.2:6379
Adding replica 192.168.144.7:6379 to 192.168.144.3:6379
Adding replica 192.168.144.5:6379 to 192.168.144.4:6379
M: 9cdad2e1e8fe6399912d2ab003694a16fb01b4d9 192.168.144.2:6379
   slots:[0-5460] (5461 slots) master
M: 1e35211766d9432edc559f37338addd017a311b2 192.168.144.3:6379
   slots:[5461-10922] (5462 slots) master
M: 97a69a5763721c466eaad8ef395f61348f1feb4a 192.168.144.4:6379
   slots:[10923-16383] (5461 slots) master
S: 6639854f65365da152b34d25eb39befe0dcb2584 192.168.144.5:6379
   replicates 97a69a5763721c466eaad8ef395f61348f1feb4a
S: 853de6f03fbc8cf7af91a8927c2700f05a2b7e0f 192.168.144.6:6379
   replicates 9cdad2e1e8fe6399912d2ab003694a16fb01b4d9
S: 238fbdbed77b1e4e51a0776b48e2236c7b0b7628 192.168.144.7:6379
   replicates 1e35211766d9432edc559f37338addd017a311b2
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
.
>>> Performing Cluster Check (using node 192.168.144.2:6379)
M: 9cdad2e1e8fe6399912d2ab003694a16fb01b4d9 192.168.144.2:6379
   slots:[0-5460] (5461 slots) master
   1 additional replica(s)
S: 6639854f65365da152b34d25eb39befe0dcb2584 192.168.144.5:6379
   slots: (0 slots) slave
   replicates 97a69a5763721c466eaad8ef395f61348f1feb4a
M: 1e35211766d9432edc559f37338addd017a311b2 192.168.144.3:6379
   slots:[5461-10922] (5462 slots) master
   1 additional replica(s)
M: 97a69a5763721c466eaad8ef395f61348f1feb4a 192.168.144.4:6379
   slots:[10923-16383] (5461 slots) master
   1 additional replica(s)
S: 853de6f03fbc8cf7af91a8927c2700f05a2b7e0f 192.168.144.6:6379
   slots: (0 slots) slave
   replicates 9cdad2e1e8fe6399912d2ab003694a16fb01b4d9
S: 238fbdbed77b1e4e51a0776b48e2236c7b0b7628 192.168.144.7:6379
   slots: (0 slots) slave
   replicates 1e35211766d9432edc559f37338addd017a311b2
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
```

動作確認

```shell
docker compose exec redis redis-cli -c -h 192.168.144.2
192.168.144.2:6379> set foo bar
-> Redirected to slot [12182] located at 192.168.144.4:6379
OK
192.168.144.4:6379> get foo
"bar"
192.168.144.4:6379> set hello world
-> Redirected to slot [866] located at 192.168.144.2:6379
OK
192.168.144.2:6379> get hello
"world"
192.168.144.2:6379>
```
