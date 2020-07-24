<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>密码找回</title>
    <script src="//unpkg.com/vue/dist/vue.js"></script>
    <script src="//unpkg.com/element-ui@2.13.2/lib/index.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="../js/jquery-3.5.1.min.js"></script>
</head>

<body>
<div id="app">
    <el-container>
        <el-header>找回密码</el-header>
        <el-main>
            <el-form :model="ruleForm" status-icon ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="手机号" prop="pass">
                    <el-input type="" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item label="验证码" prop="checkPass">
                            <el-input type="text" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item>
                            <el-button type="primary" round @click="sendMessage">发送验证码</el-button>
                        </el-form-item>

                    </el-col>
                </el-row>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">找回密码</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-main>
        <el-footer></el-footer>
    </el-container>
</div>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            ruleForm: {
                pass: '',
                checkPass: '',
                token: ''
            },
        },

        methods: {

            /**
             * 发送验证法
             */
            sendMessage() {
                axios.post('/sendyzm/' + this.ruleForm.pass)
                    .then(function (response) {
                        if (response.data.code == 200) {
                            app.$message({
                                message: '验证码发送成功！注意查收',
                                type: 'success'
                            });
                        } else {
                            app.$message.error('验证码发送失败！稍后重试');
                        }
                    })
                    .catch(function (error) {
                        app.$message.error('错误！');
                    })
            },

            submitForm() {
                axios.post('/password/' + this.ruleForm.pass + "/" + this.ruleForm.checkPass)
                    .then(function (response) {
                        console.log(response)
                        if (response.data.code == 200) {
                            app.$notify({
                                title: '成功！',
                                message: '您的密码是：' + response.data.msg + '，请妥善保管！',
                                duration: 0
                            });

                        } else {
                            app.$message.error('查询错误！请重新获得验证码！');
                        }
                    })
                    .catch(function (error) {
                        app.$message.error('请求错误！');
                    })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
    })
</script>
<style>
    @import url("//unpkg.com/element-ui@2.13.2/lib/theme-chalk/index.css");

    .el-header,
    .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 570px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>
</body>

</html>