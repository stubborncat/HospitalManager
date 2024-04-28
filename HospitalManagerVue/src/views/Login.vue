<template>
    <div class="login-index" :style="backgroundDiv">
      <img src="@/assets/logo01.jpg" alt="Description of Image" style="position: absolute; top: 0; left: 0;" />

      <div class="mid-index">

            <i
                style="
                    top: 40px;
                    font-size: 28px;
                    left: 20px;
                    position: absolute;
                "
                class="iconfont icon-r-love"
            >
                黑河学院校医院管理系统
            </i>
            <el-form
                :model="loginForm"
                :rules="loginRules"
                ref="ruleForm"
                class="loginForm"
            >
                <el-form-item prop="id">
                    <!--必须绑定v-model输入框才能输入字符---->
                    <el-input v-model="loginForm.id">
                        <i
                            slot="prefix"
                            class="iconfont icon-r-user1"
                            style="font-size: 22px"
                        ></i>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" show-password>
                        <i
                            slot="prefix"
                            class="iconfont icon-r-lock"
                            style="font-size: 22px"
                        ></i>
                    </el-input>
                </el-form-item>
                <el-form-item class="role" style="margin-left: 10px;">
                  <el-radio-group v-model="role" size="small" style="display: flex;">
                    <el-radio label="患者"></el-radio>
                    <el-radio label="医生"></el-radio>
                    <el-radio label="药局"></el-radio>
                    <el-radio label="药柜"></el-radio>
                  </el-radio-group>
                </el-form-item>

                <el-form-item class="btns">
                    <el-button
                        type="primary"
                        style="font-size: 18px"
                        @click="submitLoginForm('ruleForm')"
                    >
                        <i
                            class="iconfont icon-r-yes"
                            style="font-size: 20px"
                        ></i>
                        登录</el-button
                    >

                </el-form-item>
            </el-form>
        </div>


    </div>

</template>

<script>
import request from "@/utils/request.js";
import { setToken } from "@/utils/storage.js";
import { toLoad } from "@/utils/initialize.js";

export default {
    name: "Login",
    data() {
        var validateMoblie = (rule, value, callback) => {
            if (value === undefined) {
                callback(new Error("请输入手机号"));
            } else {
                let reg =
                    /^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,8,9])\d{8}$/;
                if (!reg.test(value)) {
                    callback(new Error("请输入合法的手机号"));
                }
                callback();
            }
        };
        var validateCard = (rule, value, callback) => {
            if (value === undefined) {
                callback(new Error("请输入身份证号"));
            } else {
                let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                if (!reg.test(value)) {
                    callback(new Error("请输入合法的身份证号码"));
                }
                callback();
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请输入密码"));
            } else {
                if (this.findForm.checkPassword !== "") {
                    this.$refs.findForm.validateField("checkPassword");
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请再次输入密码"));
            } else if (value !== this.findForm.newPassword) {
                callback(new Error("两次输入密码不一致!"));
            } else {
                callback();
            }
        };
        return {
            //背景图片
            backgroundDiv: {
                backgroundImage:
                    "url(" + require("../assets/doctor1.jpeg") + ")",
                backgroundRepeat: "no-repeat",
                backgroundSize: "100% 100%",
            },
            loginForm: {
                id: "",
                password: "",
            },
            loginRules: {
                id: [
                    {
                        required: true,
                        message: "请输入账号编号",
                        trigger: "blur",
                    },
                    {
                        min: 3,
                        max: 50,
                        message: "长度在 3到 50 个字符",
                        trigger: "blur",
                    },
                ],
                password: [
                    { required: true, message: "请输入密码", trigger: "blur" },
                ],
            },
            role: "患者",
            findRole: "患者",
            //找回密码
            findFormVisible: false,
            findForm: {
                code: "",
                newPassword: "",
                checkPassword: "",
                pEmail: "",
            },

            findRules: {
                pEmail: [
                    {
                        required: true,
                        message: "请输入邮箱地址",
                        trigger: "blur",
                    },
                    {
                        type: "email",
                        message: "请输入正确的邮箱地址",
                        trigger: ["blur", "change"],
                    },
                ],
                code: [
                    {
                        required: true,
                        message: "请输入验证码",
                        trigger: "blur",
                    },
                ],
                newPassword: [{ validator: validatePass, trigger: "blur" }],
                checkPassword: [{ validator: validatePass2, trigger: "blur" }],
            },
            totalTime: 60,
            content: "发送验证码",
            canClick: true,
            //注册
            registerFormVisible: false,
            registerForm: {
                pGender: "男",
            },
            registerRules: {
                pId: [
                    { required: true, message: "请输入账号", trigger: "blur" },
                    {
                        type: "number",
                        message: "账号必须数字类型",
                        trigger: "blur",
                    },
                ],
                pPassword: [
                    { required: true, message: "请输入密码", trigger: "blur" },
                    {
                        min: 4,
                        max: 50,
                        message: "长度在 4到 50 个字符",
                        trigger: "blur",
                    },
                ],
                pName: [
                    { required: true, message: "请输入姓名", trigger: "blur" },
                    {
                        min: 2,
                        max: 8,
                        message: "长度在 2到 8 个字符",
                        trigger: "blur",
                    },
                ],
                pEmail: [
                    { required: true, message: "请输入邮箱", trigger: "blur" },
                    {
                        type: "email",
                        message: "请输入正确的邮箱地址",
                        trigger: ["blur", "change"],
                    },
                ],
                pPhone: [{ validator: validateMoblie }],
                pCard: [{ validator: validateCard }],
                pBirthday: [
                    {
                        required: true,
                        message: "选择出生日期",
                        trigger: "blur",
                    },
                ],
            },
        };
    },
    mounted() {
        toLoad()
    },
    methods: {
        //提交表单
        submitLoginForm(formName) {
            if (!/^\d+$/.test(this.loginForm.id)) {
                this.$message.error("用户名有误");
                return;
            }
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    if (this.role === "医生") {
                        var params1 = new URLSearchParams();
                        params1.append("dId", this.loginForm.id);
                        params1.append("dPassword", this.loginForm.password);

                        request
                            .post("doctor/login", params1)
                            .then((res) => {
                                console.log(res);
                                if (res.data.status != 200)
                                    return this.$message.error(
                                        "用户名或密码错误"
                                    );
                                setToken(res.data.data.token);
                                this.$router.push("/doctorLayout");
                            })
                            .catch((e) => {
                                console.log(e);
                                if (
                                    e.response == undefined ||
                                    e.response.data == undefined
                                ) {
                                    this.$message({
                                        showClose: true,
                                        message: e,
                                        type: "error",
                                        duration: 5000,
                                    });
                                } else {
                                    this.$message({
                                        showClose: true,
                                        message: e.response.data,
                                        type: "error",
                                        duration: 5000,
                                    });
                                }
                            });
                    }
                  if (this.role === "药局") {
                    var params5 = new URLSearchParams();
                    params5.append("daId", this.loginForm.id);
                    params5.append("daPassword", this.loginForm.password);

                    request
                      .post("drugadmin/login", params5)
                      .then((res) => {
                        console.log(res);
                        if (res.data.status != 200)
                          return this.$message.error(
                            "用户名或密码错误"
                          );
                        setToken(res.data.data.token);
                        this.$router.push("/drugadminLayout");
                      })
                      .catch((e) => {
                        console.log(e);
                        if (
                          e.response == undefined ||
                          e.response.data == undefined
                        ) {
                          this.$message({
                            showClose: true,
                            message: e,
                            type: "error",
                            duration: 5000,
                          });
                        } else {
                          this.$message({
                            showClose: true,
                            message: e.response.data,
                            type: "error",
                            duration: 5000,
                          });
                        }
                      });
                  }
                  if (this.role === "药柜") {
                    var params6 = new URLSearchParams();
                    params6.append("nId", this.loginForm.id);
                    params6.append("nPassword", this.loginForm.password);

                    request
                      .post("night/login", params6)
                      .then((res) => {
                        console.log(res);
                        if (res.data.status != 200)
                          return this.$message.error(
                            "用户名或密码错误"
                          );
                        setToken(res.data.data.token);
                        this.$router.push("/drugAgainLayout");
                      })
                      .catch((e) => {
                        console.log(e);
                        if (
                          e.response == undefined ||
                          e.response.data == undefined
                        ) {
                          this.$message({
                            showClose: true,
                            message: e,
                            type: "error",
                            duration: 5000,
                          });
                        } else {
                          this.$message({
                            showClose: true,
                            message: e.response.data,
                            type: "error",
                            duration: 5000,
                          });
                        }
                      });
                  }
                    if (this.role === "患者") {
                        var params2 = new URLSearchParams();
                        params2.append("pId", this.loginForm.id);
                        params2.append("pPassword", this.loginForm.password);

                        request
                            .post("patient/login", params2)
                            .then((res) => {
                                console.log(res);
                                if (res.data.status != 200)
                                    return this.$message.error(
                                        "用户名或密码错误"
                                    );
                                setToken(res.data.data.token);
                                this.$router.push("/patientLayout");
                            })
                            .catch((e) => {
                                console.log(e);
                                if (
                                    e.response == undefined ||
                                    e.response.data == undefined
                                ) {
                                    this.$message({
                                        showClose: true,
                                        message: e,
                                        type: "error",
                                        duration: 5000,
                                    });
                                } else {
                                    this.$message({
                                        showClose: true,
                                        message: e.response.data,
                                        type: "error",
                                        duration: 5000,
                                    });
                                }
                            });
                    }
                } else {
                    console.log("error submit!!");
                    return false;
                }
            });
        },
    }
};
</script>

<style lang="scss">
.codeInput {
    width: 70%;
    margin-right: 10px;
}
.findPassword {
    margin-top: 0px;
}
.login-index {
    background: #2b4b6b;
    height: 100%;
    position: relative;
}
.mid-index {
    opacity: 0.9;
    width: 450px;
    height: 390px;
    background: white;
    //绝对定位，相对于最左上角来说
    position: absolute;
    left: 70%;
    top: 50%;
    transform: translate(-50%, -50%);
}
.logo-index {
    background: white;
    height: 130px;
    width: 130px;
    border-radius: 50%;
    padding: 10px;
    //子绝父相,使一个div悬挂在另一个div上中间
    position: absolute;
    left: 50%;
    top: 0;
    transform: translate(-50%, -50%);

    border: 1px solid #eee;
    box-shadow: 0px 0px 10px #ddd;

    img {
        height: 100%;
        width: 100%;
        border-radius: 50%;
        background: #eeeeee;
    }
}

.loginForm {
    margin-top: 120px;
}
.el-form-item {
    margin-left: 20px;
    margin-right: 20px;
}
//角色单选
.role {
    margin-left: 90px;
    margin-right: 90px;
}
//按钮
.btns {
    display: flex;
    justify-content: flex-end;
    height: 25px;
}
</style>
