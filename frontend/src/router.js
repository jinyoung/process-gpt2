
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);




import TodolistTodolistManager from "./components/listers/TodolistTodolistTable"
import TodolistTodolistDetail from "./components/listers/TodolistTodolistDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [


            {
                path: '/todolists/todolists',
                name: 'TodolistTodolistManager',
                component: TodolistTodolistManager
            },
            {
                path: '/todolists/todolists/:id',
                name: 'TodolistTodolistDetail',
                component: TodolistTodolistDetail
            },



    ]
})
