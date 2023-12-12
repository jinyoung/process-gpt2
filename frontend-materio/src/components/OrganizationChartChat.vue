<template>
    <div>
        <v-card class="chat-open-box">
            <v-alert
    type="info"
    color="deep-purple-accent-4"
    title="조직도 관리"
    text="대화형으로 조직도를 관리하십시오.
     팀(부서) 롤(역할), 직원들을 등록 수정 삭제할 수 있습니다. 예를 들어, 'OOO님을 신입사원으로 관리팀에 등록해줘. 이메일 주소는 new@company.com 이야. 역할은 개발자로 들어오셨어.'와 같은 명령을 할 수 있습니다."
  ></v-alert>

            <v-card-text class="message-box" ref="messages">
                <div v-for="(message, index) in messages"
                        :key="index"
                >
                    <div v-if="message.role == 'user'"
                            class="d-flex justify-end my-2"
                    >
                        <div class="user-message">

                            

                            {{ message.content }} 
                        </div>
                        <div class="ml-1">
                            <v-avatar size="48">
                                <v-icon>
                                    mdi-account-circle
                                </v-icon>
                            </v-avatar>
                            <div class="subtitle-2 text-center">
                                User
                            </div>
                        </div>
                    </div>

                    <div v-else-if="message.role == 'system'"
                            class="d-flex justify-start my-2"
                    >
                        <div class="mr-2">
                            <v-avatar size="48">
                                <v-icon>
                                    mdi-account-circle
                                </v-icon>
                            </v-avatar>
                            <div class="subtitle-2 text-center">
                                System
                            </div>
                        </div>
                        <div class="d-flex system-message">
                            <div v-html="message.content"></div>
                        </div>
                        <br>
                    </div>
                </div>

                <div v-if="loading" class="d-flex justify-start my-2">
                    <div class="mr-2">
                        <v-avatar size="48">
                            <v-icon>
                                mdi-account-circle
                            </v-icon>
                        </v-avatar>
                        <div class="subtitle-2 text-center">
                            System
                        </div>
                    </div>
                    <div class="d-flex system-message">
                        <v-progress-circular
                                indeterminate
                                color="grey"
                        ></v-progress-circular>
                    </div>
                </div>
            </v-card-text>

            <v-card-actions class="chat-box">

                <v-textarea
                        v-model="newMessage"
                        @keydown.enter="sendMessage"
                        label="Send Message"
                        rows="1"
                        auto-grow
                        autofocus
                >
                    <template v-slot:append-inner>
                        <v-btn @click="sendMessage"
                                color="primary"
                                icon
                                small
                        >
                            <v-icon>mdi-send</v-icon>
                        </v-btn>
                    </template>
                </v-textarea>
            </v-card-actions>
        </v-card>
    </div>
</template>

<script>
import ChatGenerator from "./ai/OrganizationChartGenerator.js";

import partialParse from "partial-json-parser";
import { VectorStorage } from "vector-storage"

export default {
    name: 'OrganizationChartChat',
    components: {
    },
    data: () => ({
        messages: [],
        newMessage: "",
        generator: null,
        loading: false,
        openChatBox: false,
        processDefinition: null,
        bpmn: null
    }),
    created() {
        this.generator = new ChatGenerator(this, {
            isStream: true,
            preferredLanguage: "Korean"
        });
        this.init();
    },
    watch: {
        messages() {
            this.$nextTick(() => {
                let messages = this.$refs.messages;
                messages.scrollTo({ top: messages.scrollHeight, behavior: 'smooth' });
            });
        },
    },
    methods:{
        handleClick() {
            this.openChatBox = !this.openChatBox;
        },
        init() {
            this.loadMessages()
        },

        sendMessage() {
            if (this.newMessage !== "") {
                this.loading = true;
                this.init();
                
                this.messages.push(
                    {
                        role: "user",
                        content: this.newMessage
                    }
                );

                this.generator.generate();
    

                this.messages.push({
                    role:'system',
                    content: '.'
                });

                this.newMessage = "";

                this.saveMessages()
            }
        },

        onModelCreated(response){

            let messageWriting = this.messages[this.messages.length -1]
            messageWriting.content = response

        },


        onGenerationFinished(responses){
            // console.log(responses);
            this.loading = false;
            if(this.processDefinition){
                this.saveDefinition(this.processDefinition)
            }
            this.saveMessages()
        },

        saveMessages(){
            window.localStorage.setItem("organization-chart-conversation", JSON.stringify(this.messages))
        },
        loadMessages(){
            this.messages = JSON.parse(window.localStorage.getItem("organization-chart-conversation"))
            if(!this.messages)
                this.messages = []

            this.generator.previousMessages = [...this.generator.previousMessages, ...this.messages]

            console.log(this.generator.previousMessages)
        },

        onError(error) {
            this.loading = false;

            if (error.code === "invalid_api_key") {
                var apiKey = prompt("API Key 를 입력하세요.");
                localStorage.setItem("openAIToken", apiKey);
                
                this.generator.generate();
                
            } else {
                console.log(error)
                var message = {
                    role:'system',
                    content: error.message
                };

                this.messages.push(message);
            }
        },


    }
}
</script>

<style scoped>
/* .chat-open-btn {
    position: fixed;
    z-index: 999;
    bottom: 15px;
    right: 15px;
} */

/* .chatgpt-icon {
    width: 30px;
    height: 30px;
} */

.chat-open-box {
    position: fixed;
    z-index: 999;
    bottom: 20px;
    width: 1211px;
    height: 600px;
}

.user-message {
    background: #9155FD;
    color: #ffffff;
    font-weight: bold;
    padding: 12px;
    border-radius: 20px;
    max-width: 90%;
}

.system-message {
    background: #eeeeee;
    font-weight: bold;
    padding: 12px;
    border-radius: 20px;
    max-width: 90%;
}

.system-message > div {
    max-width: 180px;
}

.message-box {
    overflow-y: auto;
    max-height: 80%;
}

.chat-box {
    position: absolute;
    bottom: 0px;
    right: 0px;
    width: 100%;
}

</style>