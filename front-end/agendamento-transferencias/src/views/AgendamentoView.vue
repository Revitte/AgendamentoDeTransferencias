<template>
  <div>
    <h1>Agendar Transferência</h1>
    <form @submit.prevent="agendarTransferencia">
      <input v-model="transferencia.contaOrigem" placeholder="Conta de Origem" />
      <input v-model="transferencia.contaDestino" placeholder="Conta de Destino" />
      <input v-model="transferencia.valor" type="number" placeholder="Valor" />
      <input v-model="transferencia.dataTransferencia" type="date" />
      <button type="submit">Agendar</button>
    </form>
    <p v-if="mensagem">{{ mensagem }}</p>
  </div>
</template>

<script>
import api from "@/services/api";

export default {
  data() {
    return {
      transferencia: {
        contaOrigem: "",
        contaDestino: "",
        valor: 0,
        dataTransferencia: "",
      },
      mensagem: "",
    };
  },
  methods: {
    async agendarTransferencia() {
      try {
        const response = await api.post("/transferencias", this.transferencia);
        console.log(response.data);
        this.mensagem = "Transferência agendada com sucesso!";
      } catch (error) {
        this.mensagem = "Erro ao agendar!";
      }
    },
  },
};
</script>