<template>
  <div class='bg'>
    <div class='customer'>

      <i class="el-icon-circle-check" style="font-size: 100px;color: mediumseagreen;margin-top: 120px"></i>
        <div style="margin-top: 5%" class="title">已完成填写 </div>
      <el-button type="text" @click="back()"  style="font-size: 20px;margin-top: 10px">再填一次</el-button>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    back() {
      const paperId = this.$route.params.paperId
      console.log(paperId)
      this.$router.push({ name: 'customer', params: { paperId } })
    }
  }
}
</script>

<style>
.customer > .title {
  text-align: center;
  font-size: 40px;
}
</style>
