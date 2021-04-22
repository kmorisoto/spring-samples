owaspの実行

```shell
docker run --rm -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-weekly zap-api-scan.py -t http://{IP_ADDRESS}:8080/v3/api-docs -f openapi -r testreport2.html
```
