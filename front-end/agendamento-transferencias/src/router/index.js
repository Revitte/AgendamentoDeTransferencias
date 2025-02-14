import { createRouter, createWebHistory } from "vue-router";
import AgendamentoView from "@/views/AgendamentoView.vue";

const routes = [
  { path: "/", component: AgendamentoView },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;