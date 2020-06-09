import Bar from '../../components/Bar'
import Foo from '../../components/Foo'
import Login from '../../components/Login'
import Register from '../../components/Register'
import HomeTemplate from '../../components/HomeTemplate'
import CourseList from '../../components/CourseList'
import OrderList from '../../components/OrderList'
import Course from '../../components/Course'

export default  [
  { path: '/', component: Login },
  { path: '/foo', component: Foo },
  { path: '/login', component: Login },
  { path: '/bar', component: Bar },
  { path: '/register', component: Register },
  { path: '/home', component: HomeTemplate,
    children: [
      { path: 'course/list', component: CourseList },
      { path: 'order/list', component: OrderList },
      { path: 'course/:courseNo', component: Course },
    ]
  }
  
]
