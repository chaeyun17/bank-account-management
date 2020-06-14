<template>
  <div>
    <b-container>
      <h1>계좌 목록</h1>
      <div style="margin: auto">
          <b-card
            v-for="account in accounts"
            v-bind:key="account.accountId"
            v-bind:title="`${account.purpose} (${account.bankName})`"
            class="mb-2 mx-2 pointer"
            @click="onShowDetail(account.accountId)"
            
          >
            <b-card-text  class="mb-1">{{account.number}} {{account.type}}</b-card-text>
            <b-card-text class="mb-1" style="text-align:right; font-weight:bolder; font-size:2rem">{{account.balance}} 원</b-card-text>
          </b-card>
          <b-card
            bg-variant="secondary" 
            text-variant="white"
            class="mb-2 mx-2 text-center pointer"
            @click="onRegist"
          >
          <b-icon icon="plus" font-scale="4"></b-icon>
          </b-card>
      </div>
      <hr>
      <h1 ref="sectionRegist">계좌 등록</h1>
      <div>
        <b-form @submit.prevent="onSubmit" v-if="isRegist">
          <b-form-group id="purpose-group" label="용도:" label-for="purpose">
            <b-form-input id="purpose" v-model="accountForm.purpose" type="text" required placeholder="저축, 소비, 자동이체 등"></b-form-input>
          </b-form-group>
          <b-form-group id="bankName-group" label="은행명:" label-for="bankName">
            <b-form-input id="bankName" v-model="accountForm.bankName" type="text" required placeholder="신한은행, 국민은행, 부산은행 등"></b-form-input>
          </b-form-group>
          <b-form-group id="type-group" label="계좌 유형:" label-for="type">
            <b-form-select id="type" v-model="accountForm.type" :options="accountTypes" required ></b-form-select>
          </b-form-group>
          <b-form-group id="number-group" label="계좌번호:" label-for="number">
            <b-form-input id="number" v-model="accountForm.number" type="text" required placeholder="12-123-12345"></b-form-input>
          </b-form-group>
          <b-form-group id="description-group" label="상세설명:" label-for="description">
            <b-form-input id="description" v-model="accountForm.description" type="text" required placeholder="상세설명"></b-form-input>
          </b-form-group>
          <b-form-group id="balance-group" label="잔액(원):" label-for="balance">
            <b-form-input id="balance" v-model="accountForm.balance" type="number" required placeholder="1000"></b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">제출</b-button>
        </b-form>
      </div>
    </b-container>
    <b-modal ref="account-detail-modal" centered hide-footer title="통장 상세정보">
      <div class="d-block">
        <p><span class="account-detail-col-title">ID</span>|<span class="account-detail-col-data">{{accountDetail.accountId}}</span></p>
        <p><span class="account-detail-col-title">계좌번호</span>|<span class="account-detail-col-data">{{accountDetail.number}}</span></p>
        <p><span class="account-detail-col-title">은행명</span>|<span class="account-detail-col-data">{{accountDetail.bankName}}</span></p>
        <p><span class="account-detail-col-title">잔액</span>|<span class="account-detail-col-data">{{accountDetail.balance}}</span> 원</p>
        <p><span class="account-detail-col-title">용도</span>|<span class="account-detail-col-data">{{accountDetail.purpose}}</span></p>
        <p><span class="account-detail-col-title">유형</span>|<span class="account-detail-col-data">{{accountDetail.type}}</span></p>
        <p><span class="account-detail-col-title">세부설명</span>|<span class="account-detail-col-data">{{accountDetail.description}}</span></p>
      </div>
      <b-button class="mt-3" variant="outline-danger" block @click="onDelete(accountDetail.accountId)">삭제</b-button>
      <b-button class="mt-3" variant="outline-dark" block @click="hideModal">닫기</b-button>
    </b-modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isRegist: false,
      accountTypes: [
        { text: '입출금통장', value: 'CHECKING' },
        { text: '적금통장', value: 'SAVING' },
        { text: '정기예금통장', value: 'TERM_DEPOSIT' }
      ],
      accountForm:{
        purpose: "",
        bankName: "",
        type: "",
        number: "",
        description: "",
        balance: 0,
      },
      accounts: [],
      variants:[
        'primary', 'secondary', 'success', 'info', 'warning', 'danger', 'dark'
      ],
      accountDetail: {
        accountId: 0,
        purpose: "purpose",
        bankName: "bankName",
        type: "SAVING",
        number: "11-123-12345",
        description: "desc",
        balance: 0,
      }
    }
  },
  mounted() {
    this.fetchAccounts();
  },
  methods: {
    onSubmit(){
      const baseURI = `${this.$uri}/api/accounts`;
      this.$axios.post(baseURI, this.accountForm)
                  .then(res=>{
                    console.log(res.data);
                    this.isRegist = false;
                    this.fetchAccounts();
                  })
                  .catch(err=>console.error(err));
    },
    fetchAccounts(){
      const baseURI = `${this.$uri}/api/accounts`;
      this.$axios.get(baseURI)
                  .then(res=>this.accounts = res.data)
                  .catch(err=>console.error(err));
    },
    onRegist(){
      if(!this.isRegist){
        this.$refs.sectionRegist.scrollIntoView();
      }
      this.isRegist = !this.isRegist;
    },
    randomVariant(){
      return this.variants[Math.floor(Math.random() * this.variants.length)];
    },
    hideModal(){
      this.$refs['account-detail-modal'].hide();
    },
    onShowDetail(accountId){
      this.fetchAccountDetail(accountId)
          .then(res=>{
            this.$refs['account-detail-modal'].show();
          });
    },
    fetchAccountDetail(accountId){
      const baseURI = `${this.$uri}/api/accounts/${accountId}`;
      return new Promise((resolve, reject)=>{
        this.$axios.get(baseURI)
                    .then(res=>{
                      this.accountDetail = res.data;
                      resolve(res);
                    })
                    .catch(err=>{
                      console.error(err);
                      reject(err);
                    });
      })
    },
    onDelete(accountId){
      this.deleteAccount(accountId).then(res=>{
        this.hideModal();
        this.fetchAccounts();
      });
    },
    deleteAccount(accountId){
      const baseURI = `${this.$uri}/api/accounts/${accountId}`;
      return new Promise((resolve, reject)=>{
        this.$axios.delete(baseURI)
                    .then(res=>{
                      resolve(res);
                    })
                    .catch(err=>{
                      console.error(err);
                      reject(err);
                    });
      });
    }
  },
}
</script>

<style>
  .account-detail-col-title{
    display: inline-block;
    width: 21%;
    font-weight: bolder;
    text-align: center;
  }
  .account-detail-col-data{
    padding-left: 2rem;
  }
  .pointer:hover{
    cursor: pointer;
  }
</style>