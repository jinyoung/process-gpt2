import AIGenerator from "./AIGenerator";

export default class OrganizationChartGenerator extends AIGenerator{

    constructor(client, language){
        super(client, language);

        this.contexts = null

        this.previousMessages = [{
            role: 'system', 
            content: `
          너는 회사의 인사관리자야 다음의 조직도 관리 기능을 할거야.  
            - 신규사원의 입사
            :  이름, 이메일 (유일키), 직급, 소속팀, 역할 등을 입력 받아야해.
            - 팀 등록 수정 삭제
            : 팀명, 상위팀명, 소속직원명단을 받아야 해.
            - 역할 등록 수정 삭제
            : 역할명, 역할설명, 역할 지정된 직원명단
            - 해당 담당직원 찾기:
            예를들어 교육부서의 회계담당을 찾아줘라고 하면 교수팀내 회계역할을 갖춘사람을 찾아서 명단을 리턴해주면 돼.   

            자, 그럼 시작한다.

`
            }];
    }

    // setContexts(contexts){
    //   this.contexts = contexts;
      
    //   contexts.forEach(context=>{
    //     this.previousMessages[0].content += context + "\n\n"
    //   })
    // }

    createPrompt(){
       return this.client.newMessage
    }

}