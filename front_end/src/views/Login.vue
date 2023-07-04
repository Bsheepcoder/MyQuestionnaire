<template>
  <div class="login">

      <div style="flex: 1;background-color: dodgerblue">
          <el-image style="width: 100%;height: 100%;"  fit="cover"
                    src="https://pic3.zhimg.com/c6035f76c410f00f2a1071971d643237_r.jpg?source=1940ef5c"/>
      </div>
      <div class="welcome-title" >
          <div style="font-size: 40px;font-weight: bold">欢迎进入问卷星问卷调查系统</div>
          <div style="margin-top: 10px;font-size: 20px;">结合数据平台，为客户提供问卷调查、统计研究、问卷共享等优质服务！</div>
      </div>

      <div class="plane" style="width: 450px;background-color: white;z-index: 1">
          <el-card class="board" v-show="currentPage === 'login'">
              <el-form
                      :model="loginForm"
                      status-icon
                      :rules="rules1"
                      ref="loginForm"
              >
                  <div style="margin-top: 180px;margin-bottom: 10px">
                      <div style="font-size: 30px">欢迎登录问卷星</div>
                      <div style="font-size: 14px;margin-top: 10px">在进入系统之前请先输入用户名和密码进行登录</div>
                  </div>
                  <el-form-item prop="username1">
                      <el-input
                              v-model="loginForm.username1"
                              type="text"
                              placeholder="用户名"
                              prefix-icon="el-icon-user"
                              autocomplete="off"
                      ></el-input>
                  </el-form-item>
                  <el-form-item prop="password">
                      <el-input
                              v-model="loginForm.password"
                              type="text"
                              placeholder="密码"
                              prefix-icon="el-icon-lock"
                              show-password
                              autocomplete="off"
                      ></el-input>
                      <el-button
                          style="float: right; padding: 3px 0;margin-top: 10px"
                          type="text"
                          @click="goto('register')"
                      >新用户？前往注册</el-button>
                  </el-form-item>

                  <el-form-item>
                      <el-button
                              style="width: 80%"
                              type="primary"
                              :loading="loginLoading"
                              @click="submitLogin()"
                      >登录</el-button>
                  </el-form-item>
              </el-form>
          </el-card>
          <el-card class="board" v-show="currentPage === 'register'">
              <el-form
                      :model="registerForm"
                      status-icon
                      :rules="rules2"
                      ref="registerForm"
              >
                  <div style="margin-top: 140px;margin-bottom: 10px">
                      <div style="font-size: 30px">请注册问卷星</div>
                      <div style="font-size: 14px;margin-top: 10px">请确保您填入的信息正确，并妥善保存</div>
                  </div>
                  <el-form-item prop="email">
                      <el-input
                              v-model="registerForm.email"
                              type="text"
                              placeholder="邮箱"
                              prefix-icon="el-icon-message"
                              autocomplete="off"
                      ></el-input>
                  </el-form-item>
                  <el-form-item prop="newUser">
                      <el-input
                              v-model="registerForm.newUser"
                              type="text"
                              placeholder="用户名"
                              prefix-icon="el-icon-user"
                              autocomplete="off"
                      ></el-input>
                  </el-form-item>
                  <el-form-item prop="phoneNum">
                      <el-input
                              v-model="registerForm.phoneNum"
                              type="text"
                              placeholder="手机号"
                              prefix-icon="el-icon-phone"
                              autocomplete="off"
                      ></el-input>


                  </el-form-item>
                  <el-form-item prop="newPW">
                      <el-input
                              v-model="registerForm.newPW"
                              type="text"
                              placeholder="密码"
                              prefix-icon="el-icon-lock"
                              show-password
                              autocomplete="off"
                      ></el-input>
                  </el-form-item>
                  <el-form-item prop="confirmPW">
                      <el-input
                              v-model="registerForm.confirmPW"
                              type="text"
                              placeholder="确认密码"
                              prefix-icon="el-icon-lock"
                              show-password
                              autocomplete="off"
                      ></el-input>
                      <el-button
                          style="float: right; padding: 3px 0;margin-top: 10px"
                          type="text"
                          @click="goto('login')"
                      >
                          已注册？前往登入
                      </el-button>
                  </el-form-item>
                  <el-form-item>
                      <el-button
                              style="width: 80%"
                              type="primary"
                              :loading="registerLoading"
                              @click="submitRegister()"
                      >注册</el-button>
                  </el-form-item>
              </el-form>
          </el-card>
      </div>
  </div>
</template>




<script>
import { mapActions } from 'vuex'
export default {
  name: 'Login',
  data() {
    let validateUser1 = (rule, value, callback) => {
      // const regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
      if (value !== '') {
        // console.log('success')
        callback()
      } else {
        callback(new Error('请输入用户名'))
      }
      callback()
    }

    let validateEmail = (rule, value, callback) => {
      const regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
      if (value === '') {
        callback(new Error('请输入邮箱'))
      } else if (regex.test(value)) {
        // console.log('success')
        callback()
      } else {
        callback(new Error('请输入有效邮箱'))
      }
    }

    let validateUser2 = (rule, value, callback) => {
      // const regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
      if (value !== '') {
        // console.log('success')
        callback()
      } else {
        callback(new Error('请输入用户名'))
      }
      callback()
    }

    let validatePhoneNum = (rule, value, callback) => {
      const regex = /^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$/
      if (regex.test(value) && value.length === 11) {
        callback()
      } else {
        callback(new Error('请输入有效手机号'))
      }
      callback()
    }

    let validatePass = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度至少为6位'))
      } else {
        if (this.registerForm.confirmPW !== '') {
          this.$refs.registerForm.validateField('confirmPW')
        }
        callback()
      }
    }

    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.newPW) {
        callback(new Error('两次输入密码不一致！'))
      } else {
        callback()
      }
    }

    return {
      currentPage: 'login',
      loginLoading: false,
      registerLoading: false,
      loginForm: {
        username1: '',
        password: ''
      },
      registerForm: {
        email: '',
        newUser: '',
        phoneNum: '',
        newPW: '',
        confirmPW: ''
      },
      rules1: {
        username1: [{ validator: validateUser1, trigger: 'blur' }],
        password: [{ validator: validatePass, trigger: 'blur' }]
      },
      rules2: {
        email: [{ validator: validateEmail, trigger: 'blur' }],
        newUser: [{ validator: validateUser2, trigger: 'blur' }],
        phoneNum: [{ validator: validatePhoneNum, trigger: 'blur' }],
        newPW: [{ validator: validatePass, trigger: 'blur' }],
        confirmPW: [{ validator: validatePass2, trigger: 'blur' }]
      }
    }
  },
  mounted() {
    this.goto('login')
  },
  destroyed() {
    // console.log('destroyed')
    document.onkeypress = null
  },
  methods: {
    ...mapActions([
      'login',
      'register'
    ]),
    registerKeyboardListener(page) {
      document.onkeypress = null

    },
    goto(page) {
      document.onkeypress = null
      const formName = page.charAt(0).toUpperCase() + page.substring(1)
      document.onkeypress = (e) => {
        if (e.charCode === 13) {
          this['submit' + formName]()
        }
      }
      this.currentPage = page
      this.$nextTick(() => {
        this.$refs[page + 'Form'].resetFields()
      })
    },
    submitLogin() {
      console.log('submit login')
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          // console.log(this.loginForm)
          this.login(this.loginForm).then(res => {
            if (res) {
              this.$notify.success({
                title: '登入成功'
              })
              this.$router.push({ name: 'count' })
            } else {
              this.$notify.error({
                title: '登入失败'
              })
            }
          })
        } else {
          console.log('loginForm invalid')
        }
      })
    },
    submitRegister() {
      console.log('submit register')
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          console.log(this.registerForm)
          this.register(this.registerForm).then(res => {
            if (res) {
              this.$notify.success({
                title: '注册成功'
              })
              this.goto('login')
            } else {
              this.$notify.error({
                title: '注册失败'
              })
            }
          })
        } else {
          console.log('registerForm invalid')
        }
      })
    }
  }
}
</script>

<style scoped>

.login{
    width: 100vw;height: 100vh;overflow: hidden;display: flex
}

.plane{
    width: 400px;
    text-align: center;
}

.board {
    text-align: center;
    height: 100%;
}

.title {
    font-size: 40px;
}

.welcome-title{
    position: absolute;
    bottom:30px;
    left: 30px;
    color:white;
    text-shadow: 0 0 10px black;
}
</style>
