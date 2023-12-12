<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="updateTodolistDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('User')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>Todolist 업데이트
                </v-btn>
                <v-dialog v-model="updateTodolistDialog" width="500">
                    <UpdateTodolist
                        @closeDialog="updateTodolistDialog = false"
                        @updateTodolist="updateTodolist"
                    ></UpdateTodolist>
                </v-dialog>
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>액티비티 Id</th>
                        <th>액티비티 이름</th>
                        <th>시작일</th>
                        <th>완료일</th>
                        <th>마감일</th>
                        <th>프로세스 정의Id</th>
                        <th>프로세스 인스턴스Id</th>
                        <th>사용자 Id</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" :key="val" @click="changeSelectedRow(val)" :style="val === selectedRow ? 'background-color: #f0f3ff;':''">
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="액티비티 Id">{{ val.activityId }}</td>
                            <td class="whitespace-nowrap" label="액티비티 이름">{{ val.activityName }}</td>
                            <td class="whitespace-nowrap" label="시작일">{{ val.startDate }}</td>
                            <td class="whitespace-nowrap" label="완료일">{{ val.endDate }}</td>
                            <td class="whitespace-nowrap" label="마감일">{{ val.dueDate }}</td>
                            <td class="whitespace-nowrap" label="프로세스 정의Id">{{ val.processDefinitionId }}</td>
                            <td class="whitespace-nowrap" label="프로세스 인스턴스Id">{{ val.processInstanceId }}</td>
                            <td class="whitespace-nowrap" label="사용자 Id">{{ val.userId }}</td>
                            <Icon style="margin-top: 15px;" icon="mi:delete" @click="deleteRow(val)" />
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Todolist 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Todolist :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Todolist 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <String label="액티비티 Id" v-model="selectedRow.activityId" :editMode="true"/>
                            <String label="액티비티 이름" v-model="selectedRow.activityName" :editMode="true"/>
                            <Date label="시작일" v-model="selectedRow.startDate" :editMode="true"/>
                            <Date label="완료일" v-model="selectedRow.endDate" :editMode="true"/>
                            <Date label="마감일" v-model="selectedRow.dueDate" :editMode="true"/>
                            <String label="프로세스 정의Id" v-model="selectedRow.processDefinitionId" :editMode="true"/>
                            <String label="프로세스 인스턴스Id" v-model="selectedRow.processInstanceId" :editMode="true"/>
                            <String label="사용자 Id" v-model="selectedRow.userId" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import DrawerContent from '../../../layouts/components/DrawerContent.vue';
import BaseGrid from '../base-ui/BaseGrid.vue'
import Todolist from '../Todolist.vue'
import String from '../primitives/String.vue'
import Date from '../primitives/Date.vue'
import UpdateTodolist from '../UpdateTodolist.vue'

export default {
    name: 'todolistGrid',
    mixins:[BaseGrid],
    components:{
        DrawerContent,
        Todolist,
        String,
        Date,
        UpdateTodolist,
    },
    data: () => ({
        path: 'todolists',
        updateTodolistDialog: false,
    }),
    watch: {
    },
    methods:{
        updateTodolist(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "updateTodolist", params)
                this.$EventBus.$emit('show-success','UpdateTodolist 성공적으로 처리되었습니다.')
                this.updateTodolistDialog = false
            }catch(e){
                console.log(e)
            }
            
        },
    }
}

</script>