<script setup>
import { VerticalNavLink, VerticalNavSectionTitle } from '@layouts'

// import { VectorStorage } from "vector-storage";
// import partialParse from "partial-json-parser";
// const openAIToken = localStorage.getItem("openAIToken");
// const vectorStore = new VectorStorage({ openAIApiKey: openAIToken });
// const results = await vectorStore.similaritySearch({
//     query: "processDefinitionName:"
// });
// let processList = [];
// results.similarItems.forEach(item => {
//     const jsonProcess = partialParse(item.text);
//     processList.push(jsonProcess);
// });
// console.log(processList)
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
        <!-- <VerticalNavLink
            v-for="process in processList"
            :key="process.processDefinition"
            :item="{
                title: process.processDefinitionName,
                to: `/process/${process.processDefinition}`,
            }"
        /> -->
        
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
