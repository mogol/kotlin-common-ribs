package com.example.germansaprykin.kotlinribs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sample.root.OSSpecificDependencies
import sample.root.RootBuilder
import sample.root.RootRouter

class MainActivity : AppCompatActivity() {

    private lateinit var rootRouter: RootRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dependencies = OSSpecificDependencies(this)
        rootRouter = RootBuilder(dependencies).build()
        rootRouter.activate()
        setContentView(rootRouter.view)
    }
}
