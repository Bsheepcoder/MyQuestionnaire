<template>
  <el-container class="editor" >
    <el-header height="70px">
      <div class="editor-header" style="font-size: 25px;display: flex;align-items: center;justify-content: space-between">
        <span style="float: left; ">
          <i class="el-icon-star-off" style="color: yellow;"></i>
          问卷星官网
        </span>
        <div style="display:flex;align-items: center;justify-content:space-between;width:130px">
            <el-avatar size="15"  src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
            <el-dropdown trigger="click" style="font-size: 18px;">
                <span class="el-dropdown-link">
                  用户名<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item icon="el-icon-plus">个人信息</el-dropdown-item>
                    <el-dropdown-item icon="el-icon-circle-check"><span style="cursor: pointer" @click="logout()">退出登录</span></el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
      </div>
    </el-header>
    <el-container style="background-color: #f7f8fa;">
        <el-aside style="background-color: #ffffff; margin: 20px; width: 250px;border-radius: 10px">
          <el-menu @select="handleMenuSelect" default-active="overview">
            <el-menu-item index="overview">
              <i class="el-icon-coin"></i>
              <span class="select">问卷统计</span>
            </el-menu-item>
            <el-menu-item index="list">
                <i class="el-icon-coin"></i>
                <span class="select">问卷列表</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-container style="background-color: #ffffff;margin: 20px;border-radius: 10px">
            <el-main>
                <router-view></router-view>
                <CreatePaper></CreatePaper>
            </el-main>
        </el-container>
    </el-container>
  </el-container>
</template>

<script>
import CreatePaper from './create/CreatePaper'
import { mapMutations, mapGetters, mapActions } from 'vuex'

export default {
  name: 'Editor',
  components: {
    CreatePaper
  },
  computed: {
    ...mapGetters(['userInfo'])
  },
  methods: {
    ...mapMutations([
      'set_createPaperVisible',
      'set_loginState',
      'set_userInfo'
    ]),
    ...mapActions(['logoutAct']),
    handleMenuSelect(index) {
      if (index === 'overview') {
        const route = this.$route.path
        if (route !== '/editor/overview') {
          this.$router.push({ name: index })
        }
      } else if (index === 'create') {
        this.set_createPaperVisible(true)
      }
    },
    logout() {
      console.log('logout')
      this.logoutAct().then(res => {
        if(res) {
          this.$router.go({ name: 'login' })
        }
      })
      // this.$router.push('/login')
    }
  }
}
</script>

<style>
* {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen,
    Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}

.select{
    font-size: 20px;
}

.editor {
  height: 100%;
}

.editor-header {
    padding: 10px;
    margin: 0 0 50px 20px;

}


.el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
}
.el-icon-arrow-down {
    font-size: 12px;
}
</style>
