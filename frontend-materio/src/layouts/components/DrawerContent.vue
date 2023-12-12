<script setup>
import { VerticalNavLink, VerticalNavSectionTitle } from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '프로세스 관리' }" />
        <VerticalNavLink
            :item="{
                title: '프로세스 관리',
                to: '/managements',
                icon: 'mdi-plus'
            }"
        />
        
        <VerticalNavSectionTitle :item="{ heading: '프로세스 실행' }" />
        <VerticalNavLink
            :item="{
                title: '프로세스 실행',
                to: '/executes',
                icon: 'mdi-plus'
            }"
        />
        
        <VerticalNavSectionTitle :item="{ heading: 'ToDoList' }" />
        <VerticalNavLink
            :item="{
                title: '할 일',
                to: '/todolists',
            }"
        />
    </ul>
</template>
