<template>
  <div>
      <el-container class="overview" >

      <el-header height="30px">
          <span class="title" style="float: left; font-size: 30px; font-weight: 700"
          >我的问卷</span>
          <div>
              <el-button type="primary" style="float: right" @click="createPaper()"
              ><i class="el-icon-document-add" style="font-size: 18px" > 新增问卷</i> </el-button
              >
          </div>
      </el-header>
      <el-divider>
        欢迎登录问卷星~
      </el-divider>
      <el-main class="papers"  style="text-align: center;display: flex;height: 100%">
          <h1 v-if="allPapers.length === 0"> 无问卷信息，点击<span @click="createPaper()" style="cursor: pointer; color: blue;">新增问卷</span></h1>
          <div class="plane">
              <div v-for="(paper, index) in startPapers" :key="paper.id" style="display:block">
                  <el-card class="paper_card" style="border-radius: 12px;">
                      <div slot="header" class="clearfix">

                          <!--                      <span class="id" ><i class="el-icon-price-tag">{{ paper.id }} </i></span>-->
                          <span class="id" ><i class="el-icon-caret-right" style="color:mediumspringgreen;"> </i>{{ paper.title }}</span>

                          <label v-if="paper.status === 'INIT'" style="float: right;color: mediumseagreen"
                          >编辑中</label
                          >
                          <label v-else-if="paper.status === 'START'" style="float: right;color: blue">
                              已发放
                          </label>
                          <label v-else-if="paper.status === 'STOP'" style="float: right;color:orange">
                              已回收
                          </label>
                      </div>
                      <el-button
                              v-if="paper.status === 'INIT'"
                              type="text"
                              style="float: left"
                              @click="editPaper(paper.id)"
                              icon="el-icon-edit"
                      >继续编辑</el-button
                      >
                      <el-button
                              v-if="paper.status === 'START' || paper.status === 'STOP'"
                              type="text"
                              style="float: left;color: green"
                              @click="checkPaperDetail(paper.id)"
                              icon="el-icon-edit"
                      >查看问卷</el-button
                      >
                      <el-button
                              v-if="paper.status === 'START'"
                              type="text"
                              style="float: left;"
                              @click="handleShare(paper.id)"
                              icon="el-icon-share"
                      >发放链接</el-button
                      >
                      <el-button
                              type="text"
                              style="float: left;color: red"
                              @click="showDeleteDialog(index)"
                              icon="el-icon-delete"
                      >删除问卷</el-button
                      >
                  </el-card>
              </div>
          </div>
          <div  class="plane">
              <div v-for="(paper, index) in initPapers" :key="paper.id" style="display:block;">
                  <el-card class="paper_card" style="border-radius: 12px;">
                      <div slot="header" class="clearfix">

                          <!--                      <span class="id" ><i class="el-icon-price-tag">{{ paper.id }} </i></span>-->
                          <span class="id" ><i class="el-icon-caret-right" style="color:mediumspringgreen;"> </i>{{ paper.title }}</span>

                          <label v-if="paper.status === 'INIT'" style="float: right;color: mediumseagreen"
                          >编辑中</label
                          >
                          <label v-else-if="paper.status === 'START'" style="float: right;color: blue">
                              已发放
                          </label>
                          <label v-else-if="paper.status === 'STOP'" style="float: right;color:orange">
                              已回收
                          </label>
                      </div>
                      <el-button
                              v-if="paper.status === 'INIT'"
                              type="text"
                              style="float: left;"
                              @click="editPaper(paper.id)"
                              icon="el-icon-edit"
                      >继续编辑</el-button
                      >
                      <el-button
                              v-if="paper.status === 'START' || paper.status === 'STOP'"
                              type="text"
                              style="float: left;color: green"
                              @click="checkPaperDetail(paper.id)"
                              icon="el-icon-edit"
                      >查看问卷</el-button
                      >
                      <el-button
                              v-if="paper.status === 'START'"
                              type="text"
                              style="float: left;"
                              @click="handleShare(paper.id)"
                              icon="el-icon-share"
                      >发放链接</el-button
                      >
                      <el-button
                              type="text"
                              style="float: left;color: red"
                              @click="showDeleteDialog(index)"
                              icon="el-icon-delete"
                      >删除问卷</el-button
                      >
                  </el-card>
              </div>
          </div >
          <div  class="plane">
              <div v-for="(paper, index) in endPapers" :key="paper.id" style="display:block">
                  <el-card class="paper_card" style="border-radius: 12px;">
                      <div slot="header" class="clearfix">

                          <!--                      <span class="id" ><i class="el-icon-price-tag">{{ paper.id }} </i></span>-->
                          <span class="id" ><i class="el-icon-caret-right" style="color:mediumspringgreen;"> </i>{{ paper.title }}</span>

                          <label v-if="paper.status === 'INIT'" style="float: right;color: mediumseagreen"
                          >编辑中</label
                          >
                          <label v-else-if="paper.status === 'START'" style="float: right;color: blue">
                              已发放
                          </label>
                          <label v-else-if="paper.status === 'STOP'" style="float: right;color:orange">
                              已回收
                          </label>
                      </div>
                      <el-button
                              v-if="paper.status === 'INIT'"
                              type="text"
                              style="float: left"
                              @click="editPaper(paper.id)"
                              icon="el-icon-edit"
                      >继续编辑</el-button
                      >
                      <el-button
                              v-if="paper.status === 'START' || paper.status === 'STOP'"
                              type="text"
                              style="float: left;color: green"
                              @click="checkPaperDetail(paper.id)"
                              icon="el-icon-edit"
                      >查看问卷</el-button
                      >
                      <el-button
                              v-if="paper.status === 'START'"
                              type="text"
                              style="float: left;"
                              @click="handleShare(paper.id)"
                              icon="el-icon-share"
                      >发放链接</el-button
                      >
                      <el-button
                              type="text"
                              style="float: left;color: red"
                              @click="showDeleteDialog(index)"
                              icon="el-icon-delete"
                      >删除问卷</el-button
                      >
                  </el-card>
              </div>
          </div>

          <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
              <span>确认删除该问卷</span>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="danger" @click="handleDelete()"
                >确 定</el-button
                >
              </span>
          </el-dialog>
      </el-main>

      </el-container>
  </div>

</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  name: 'Overview',
  data() {
    return {
      dialogVisible: false,
      userId: -1,
      deleteDialogInfo: {
        index: -1
      }
    }
  },
  mounted() {
    // console.log('mounted')
    this.getAllPapers()
    this.getAllPapers()
    this.getStartPapers()
    this.getEditPapers()
    this.getEndPapers()
  },
  computed: {
      ...mapGetters(['allPapers','startPapers','initPapers','endPapers'])
  },
  methods: {
      ...mapActions(['getAllPapers', 'deletePaper', 'editOldPaper','getStartPapers','getEditPapers','getEndPapers']),
    ...mapMutations(['set_createPaperVisible']),
    createPaper() {
      this.set_createPaperVisible(true)
    },
    checkPaperDetail(paperId) {
      console.log(`check paper detail with paperId: ${paperId}`)
      this.$router.push({ name: 'monitor', params: { paperId } })
    },
    handleShare(paperId) {
      this.$router.push({ name: 'paperlink', params: { paperId } })
    },
    editPaper(paperId) {
      this.editOldPaper(paperId).then(res => {
        if(res) {
          this.$router.push(`/editor/create/${paperId}`)
        } else {
          this.$notify.success({
            title: '异常问卷，请询问管理员'
          })
        }
      })
    },
    handleDelete() {
      const index = this.deleteDialogInfo.index
      console.log(`delete paper with index: ${index}`)
      console.log(this.allPapers[index])
      this.deletePaper(index).then(res => {
        if(res) {
          this.$notify.success({
            title: '删除成功'
          })
        } else {
          this.$notify.error({
            title: '删除失败'
          })
        }
      })
      this.dialogVisible = false
    },
    showDeleteDialog(index) {
      this.deleteDialogInfo.index = index
      this.dialogVisible = true
    }
  }
}
</script>

<style>
.id{
    margin-right: 10px;
    font-size: 20px;
}

.paper_card {
    margin-bottom:20px;
    justify-content: space-between;
    text-align: left;
    font-weight: 600;
    width: 480px;
    font-size: 20px;
}

.like {
    //cursor: pointer;
    font-size: 25px;
    //display: inline-block;
}

.plane{
    margin: 10px;
}
</style>
