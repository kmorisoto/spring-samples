package org.example.helloshell

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class MyCommands {

    @ShellMethod(key = ["hello-world"])
    fun helloWorld(
        @ShellOption(defaultValue = "spring") arg: String
    ): String = "Hello $arg"
}
