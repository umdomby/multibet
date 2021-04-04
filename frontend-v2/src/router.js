import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Vladilen from '@/components/Vladilen'

import store from './store'

Vue.use(Router);

const router = new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
        { path: '/', component: Hello },
        { path: '/v', component: Vladilen },

        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});

export default router;