<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
      <h1 class="text-2xl font-bold text-center text-gray-700 mb-6">Agendar Transferência</h1>
      
      <form @submit.prevent="agendarTransferencia" class="space-y-4">
        <input 
          v-model="transferencia.contaOrigem"
          type="text"
          inputmode="numeric"
          pattern="[0-9]*"
          maxlength="10"
          @input="enforceMaxLength('contaOrigem')"
          class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" 
          placeholder="Conta de Origem"
        />
        <input 
          v-model="transferencia.contaDestino"
          type="text"
          inputmode="numeric"
          pattern="[0-9]*"
          maxlength="10"
          @input="enforceMaxLength('contaDestino')"
          class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" 
          placeholder="Conta de Destino"
        />
        <input 
          v-model="transferencia.valor" 
          type="number" 
          class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" 
          placeholder="Valor"
        />
        <input 
          v-model="transferencia.dataTransferencia" 
          type="date" 
          class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <button 
          type="submit" 
          class="w-full bg-blue-500 text-white py-3 rounded-lg font-semibold hover:bg-blue-600 transition duration-300"
        >
          Agendar
        </button>
      </form>

      <p v-if="mensagem" class="mt-4 text-center font-medium" :class="mensagem.includes('sucesso') ? 'text-green-500' : 'text-red-500'">
        {{ mensagem }}
      </p>

      <button 
        @click="buscarTransferencias" 
        class="w-full mt-4 bg-green-500 text-white py-3 rounded-lg font-semibold hover:bg-green-600 transition duration-300"
      >
        Buscar Transferências Agendadas
      </button>

      <ul v-if="transferenciasAgendadas.length" class="mt-4 bg-gray-200 p-4 rounded-lg">
        <li v-for="(t, index) in transferenciasAgendadas" :key="index" class="border-b py-2">
            <strong>Origem:</strong> {{ t.contaOrigem || 'N/A' }} - 
            <strong>Destino:</strong> {{ t.contaDestino || 'N/A' }} - 
            <strong>Valor:</strong> R$ {{ t.valor }} - 
            <strong>Data Transferência:</strong> {{ t.dataAgendamento }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
    transferencia: {
        valor: 0,
        dataTransferencia: "",
        status: "AGENDADA"
      },
      mensagem: "",
      transferenciasAgendadas: [],
    };
  },
  methods: {
    enforceMaxLength(campo) {
      this.transferencia[campo] = String(this.transferencia[campo] || '')
        .replace(/\D/g, '')
        .slice(0, 10);
    },
    calcularTaxa() {
      const hoje = new Date();
      hoje.setHours(0, 0, 0, 0);
      const dataTransferencia = new Date(this.transferencia.dataTransferencia);
      dataTransferencia.setHours(0, 0, 0, 0);
      const diferencaDias = Math.ceil((dataTransferencia - hoje) / (1000 * 60 * 60 * 24));

      let taxa = 0;
      let valorFixo = 0;

      if (diferencaDias >= 0 ) { taxa = 2.5; valorFixo = 3; }
      else if (diferencaDias >= 1 && diferencaDias <= 10) { taxa = 0; valorFixo = 12; }
      else if (diferencaDias >= 11 && diferencaDias <= 20) { taxa = 8.2; valorFixo = 0; }
      else if (diferencaDias >= 21 && diferencaDias <= 30) { taxa = 6.9; valorFixo = 0; }
      else if (diferencaDias >= 31 && diferencaDias <= 40) { taxa = 4.7; valorFixo = 0; }
      else if (diferencaDias >= 41 && diferencaDias <= 50) { taxa = 1.7; valorFixo = 0; }
      else {
        return { valido: false };
      }

      const taxaCalculada = (this.transferencia.valor * (taxa / 100)) + valorFixo;
      return { valido: true, taxa: taxaCalculada.toFixed(2) };
    },
    async agendarTransferencia() {
      const resultadoTaxa = this.calcularTaxa();
      if (!resultadoTaxa.valido) {
        this.mensagem = "Erro: Não há taxa aplicável para esta data de transferência.";
        return;
      }
      const transferenciaData = {
        valor: this.transferencia.valor,
        dataTransferencia: this.transferencia.dataTransferencia,
        status: "AGENDADA",
      };
      try {
        const response = await axios.post("http://localhost:8080/transferencias", transferenciaData, {
            headers: {
              "Content-Type": "application/json",
            },
        });

        if (response.status === 201 || response.status === 200) {
            this.mensagem = `Transferência agendada com sucesso! Taxa: R$ ${resultadoTaxa.taxa}`;
            this.transferencia.valor = 0;
            this.transferencia.contaOrigem = "";
            this.transferencia.contaDestino = "";

            this.buscarTransferencias();
        }
      } catch (error) {
        console.error("Erro ao agendar transferência:", error);
        this.mensagem = "Erro ao agendar transferência!";
      }
    },
    async buscarTransferencias() {
      try {
        const response = await axios.get("http://localhost:8080/transferencias");
        this.transferenciasAgendadas = response.data;   
      } catch (error) {
        console.error("Erro ao buscar transferências", error);
      }
    }
  }
};
</script>
