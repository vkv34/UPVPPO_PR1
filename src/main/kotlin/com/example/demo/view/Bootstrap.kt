package com.example.demo.view

import kotlinx.html.*
import kotlinx.html.stream.createHTML

fun HEAD.getBootstrap() =
    unsafe {
        +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9\" crossorigin=\"anonymous\">"
        +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm\" crossorigin=\"anonymous\"></script>"
    }

fun basePage(body: BODY. () -> Unit) = createHTML().html {
    head {
        getBootstrap()
    }
    body {
        body()
    }
}