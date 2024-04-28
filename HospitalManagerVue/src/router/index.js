import Vue from "vue";
import VueRouter from "vue-router";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import "@/assets/css/global.css";
import Login from "@/views/Login.vue";
import Admin from "@/views/Admin.vue";
import Doctor from "@/views/Doctor.vue";
import Patient from "@/views/Patient.vue";
import PatientList from "@/views/PatientList.vue";
import DoctorList from "@/views/DoctorList.vue";
import OrderList from "@/views/OrderList.vue";
import {getToken} from "@/utils/storage.js";
import OrderOperate from "@/views/OrderOperate.vue";
import SectionMessage from "@/views/SectionMessage.vue";
import MyOrder from "@/views/MyOrder.vue";
import OrderToday from "@/views/OrderToday.vue";
import DealOrder from "@/views/DealOrder.vue";
import DrugList from "@/views/DrugList.vue";
import CheckList from "@/views/CheckList.vue";
import DoctorOrder from "@/views/DoctorOrder.vue";
import ArrangeIndex from "@/views/ArrangeIndex.vue";
import SectionList from "@/views/SectionList.vue";
import AdminLayout from "@/views/AdminLayout.vue";
import DoctorLayout from "@/views/DoctorLayout.vue";
import PatientLayout from "@/views/PatientLayout.vue";
import SectionIndex from "@/views/SectionIndex.vue";
import ArrangeDoctor from "@/views/ArrangeDoctor.vue";
import echarts from 'echarts';//引入echarts
import DealOrderAgain from "@/views/DealOrderAgain.vue";
import DoctorCard from "@/views/DoctorCard.vue";
import PatientCard from "@/views/PatientCard.vue"
import Pharmacy from "@/views/Pharmacy.vue";
import PharmacyLayout from "@/views/PharmacyLayout.vue";
import pharmacy from "@/views/Pharmacy.vue";
import PharmacyList from "@/views/PharmacyList.vue";
import EquipmentList from "@/views/EquipmentList.vue";
import Equipment from "@/views/Equipment.vue";
import EquipmentLayout from "@/views/EquipmentLayout.vue";
import drugadminList from "@/views/DrugadminList.vue";
import DrugAdmin from "@/views/DrugAdmin.vue";
import DrugadminLayout from "@/views/DrugadminLayout.vue";
import DrugadminList from "@/views/DrugadminList.vue";
import buyList from "@/views/BuyList.vue";
import BuyList from "@/views/BuyList.vue";
import BuyListAgain from "@/views/BuyListAgain.vue";
import DrugAgain from "@/views/DrugAgain.vue";
import DrugAgainLayout from "@/views/DrugAgainLayout.vue";
import NOrderList from "@/views/NOrderList.vue";
import NightList from "@/views/NightList.vue";
import DrugAgntList from "@/views/DrugAgntList.vue";
import department from "@/views/Department.vue";
import DepartmentList from "@/views/DepartmentList.vue";
import Login1 from "@/views/Login1.vue";
import DepartmentListN from "@/views/DepartmentListN.vue";

Vue.prototype.$echarts = echarts;//引入echarts
Vue.use(ElementUI);
Vue.use(VueRouter);

const routes = [
  {
    path: "*",
    redirect:"/login"

  },
  {
    path: "*",
    redirect:"/login1"

  },
  {
    path: "/login",
    //redirect: "/login",//设置默认跳转路径
    component: Login
  },
  {
    path: "/login1",
    //redirect: "/login",//设置默认跳转路径
    component: Login1
  },
  {
    path: "/admin",
    component: Admin,
    meta: {
      requireAuth: true,  // 该路由项需要权限校验
    },
    children:[
      {
        path: "/adminLayout",
        component: AdminLayout,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
      path: "/doctorList",
      component: DoctorList,
      meta: {
        requireAuth: true,  // 该路由项需要权限校验
      },
    },
      {
        path: "/pharmacyList",
        component: PharmacyList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "/DepartmentList",
        component: DepartmentList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "/drugadminList",
        component: drugadminList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "/equipmentList",
        component: EquipmentList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
    {
      path: "/patientList",
      component: PatientList,
      meta: {
        requireAuth: true,  // 该路由项需要权限校验
      },
    },
      {
        path: "/nightList",
        component: NightList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "/norderList",
        component: NOrderList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
    {
      path: "/orderList",
      component: OrderList,
      meta: {
        requireAuth: true,  // 该路由项需要权限校验
      },
    },
      {
        path: "/drugagntList",
        component: DrugAgntList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
    {
      path: "/drugList",
      component: DrugList,
      meta: {
        requireAuth: true,  // 该路由项需要权限校验
      },
    },
      {
        path: "/buyListAgain",
        component: BuyListAgain,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
    {
      path: "/checkList",
      component: CheckList,
      meta: {
        requireAuth: true,  // 该路由项需要权限校验
      },
      
    },

    {
      path: "/arrangeIndex",
      component: ArrangeIndex,
      meta: {
        requireAuth: true,  // 该路由项需要权限校验
      },
      children:[
        {
          path: "/sectionIndex",
          component: SectionIndex,
          meta: {
            requireAuth: true,  // 该路由项需要权限校验
          },
        },
        {
          path: "/arrangeDoctor",
          component: ArrangeDoctor,
          meta: {
            requireAuth: true,  // 该路由项需要权限校验
          },
        },
      ]
    },
    {
      path: "/sectionList",
      component: SectionList,
      meta: {
        requireAuth: true,  // 该路由项需要权限校验
      },
     
      
    },
  ]
  },
  {
    path: "/patient",
    component: Patient,
    meta: {
      requireAuth: true,  // 该路由项需要权限校验
    },
    children:[
      {
        path: "/patientLayout",
        component: PatientLayout,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "/buyList",
        component: BuyList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "/orderOperate",
        component: OrderOperate
      },
      {
        path: "/sectionMessage",
        component: SectionMessage
      },
      {
        path: "/myOrder",
        component: MyOrder
      },
      {
        path: "/patientCard",
        component: PatientCard,
      }
    ]
  },
  {
    path: "/pharmacy",
    component: Pharmacy,
    meta: {
      requireAuth: true,  // 该路由项需要权限校验
    },
    children:[
      {
        path: "/pharmacyLayout",
        component: PharmacyLayout,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "drugList",
        component: DrugList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },

    ]
  },
  {
    path: "/drugadmin",
    component: DrugAdmin,
    meta: {
      requireAuth: true,  // 该路由项需要权限校验
    },
    children:[
      {
        path: "/drugadminLayout",
        component: DrugadminLayout,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "drugadminList",
        component: DrugadminList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "DepartmentList",
        component: DepartmentList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "orderList",
        component: OrderList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "buyListAgain",
        component: BuyListAgain,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },

    ]
  },
  {
    path: "/drugAgain",
    component: DrugAgain,
    meta: {
      requireAuth: true,  // 该路由项需要权限校验
    },
    children:[
      {
        path: "/drugAgainLayout",
        component: DrugAgainLayout,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "nightList",
        component: NightList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "norderList",
        component: NOrderList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "DepartmentListN",
        component: DepartmentListN,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
    ]
  },
  {
    path: "/equipment",
    component: Equipment,
    meta: {
      requireAuth: true,  // 该路由项需要权限校验
    },
    children:[
      {
        path: "/equipmentLayout",
        component: EquipmentLayout,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "checkList",
        component: CheckList,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },

    ]
  },
  {
    path: "/doctor",
    component: Doctor,
    meta: {
      requireAuth: true,  // 该路由项需要权限校验
    },
    children:[
      {
        path: "/doctorLayout",
        component: DoctorLayout,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "/department",
        component: department,
        meta: {
          requireAuth: true,  // 该路由项需要权限校验
        },
      },
      {
        path: "/orderToday",
        component: OrderToday,
    },
    {
      path: "/dealOrder",
      component: DealOrder
    },
    {
      path: "/dealOrderAgain",
      component: DealOrderAgain
    },
    {
      path: "/doctorOrder",
      component: DoctorOrder,
  },
{
  path: "/doctorCard",
  component: DoctorCard,
}
    ],

  }
];



const router = new VueRouter({
  routes
});
//没登录的情况下，访问任何一个页面都会返回登录页面
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    const token = getToken();
    if (token !== null) {
      //直接放行
      next();
  } else {
      next("/login");
    }
  }
  else{
    next();
  }
  });
export default router;
