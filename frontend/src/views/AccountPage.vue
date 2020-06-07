<template>
  <div>
    <b-container>
      <h1>계좌 등록</h1>
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      isRegist: true,
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
    }
  },
  mounted() {
    
  },
  methods: {
    onSubmit(){
      const baseURI = this.$uri + '/api/accounts';
      
        this.$axios.post(baseURI, this.accountForm)
                    .then(result=>console.log(result));
        // fetch('http:///api/accounts')
        //   .catch(err=>{console.log(err)})
        //   .then(reponse=>console.log(response))
    }
  },
}
</script>

<style>

</style>