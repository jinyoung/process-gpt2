<template>
    <div>
        <vue-bpmn :key="processDefinition.length"
                :bpmn="bpmn"
                :options="options"
                v-on:error="handleError"
                v-on:shown="handleShown"
                v-on:loading="handleLoading"
        ></vue-bpmn>
    </div>
</template>

<script>
import VueBpmn from './Bpmn.vue';
import partialParse from "partial-json-parser";
import { VectorStorage } from "vector-storage";

export default {
    name: "ProcessDefinition",
    components: {
        VueBpmn
    },
    props: {
        processDefinition: Object,
        bpmn: Object
    },
    data: () => ({
    }),
    created() {
        this.init();
    },
}
</script>