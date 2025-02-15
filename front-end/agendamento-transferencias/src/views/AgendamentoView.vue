<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
      <h1 class="text-2xl font-bold text-center text-gray-700 mb-6">Agendar Transferência</h1>
      <form @submit.prevent="agendarTransferencia" class="space-y-4">
        <input 
          v-model="transferencia.contaOrigem"
          type="number"
          inputmode="numeric"
          pattern="[0-9]*"
          @input="enforceMaxLength('contaOrigem')"
          class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" 
          placeholder="Conta de Origem"
          maxlength="10"
        />
        <input 
          v-model="transferencia.contaDestino"
          type="number"
          inputmode="numeric"
          pattern="[0-9]*"
          @input="enforceMaxLength('contaDestino')"
          class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" 
          placeholder="Conta de Destino"
          maxlength="10"
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
    </div>
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
            enforceMaxLength(campo) {
                this.transferencia[campo] = String(this.transferencia[campo] || '') // Garante que seja uma string
                  .replace(/\D/g, '') // Remove qualquer caractere que não seja número
                  .slice(0, 10); // Garante no máximo 10 dígitos
            },
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
    }
</script>