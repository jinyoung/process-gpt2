<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: 'Management' }" />
        <VerticalNavSectionTitle :item="{ heading: 'Execute' }" />
        <VerticalNavSectionTitle :item="{ heading: 'Todolist' }" />
        <VerticalNavLink
            :item="{
                title: 'Todolist',
                to: '/todolists',
            }"
        />
    </ul>
</template>
