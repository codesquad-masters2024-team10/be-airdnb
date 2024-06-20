<script>
    export let close;
  
    let email = '';
    let authCode = '';
    let password = '';
    let isAuthCodeVisible = false;
  
    function handleAuth() {
      if (email) {
        isAuthCodeVisible = true;
      }
    }
  
    function handleRegister() {
      // 회원가입 처리 로직
      close();
    }
  </script>
  
  <div class="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50">
    <div class="bg-white p-6 rounded-lg shadow-lg w-80 space-y-4">
      <div class="flex justify-between items-center">
        <h2 class="text-xl font-bold">Admin Register 📝</h2>
        <button on:click={close} class="text-gray-500 hover:text-gray-700">&times;</button>
      </div>
      <div>
        <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
        <input
          id="email"
          type="email"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          bind:value={email}
          disabled={isAuthCodeVisible}
        />
      </div>
      {#if isAuthCodeVisible}
        <div>
          <label for="authCode" class="block text-sm font-medium text-gray-700">Auth Code</label>
          <input
            id="authCode"
            type="text"
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            bind:value={authCode}
          />
        </div>
        <div>
          <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
          <input
            id="password"
            type="password"
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            bind:value={password}
          />
        </div>
        <button
          class="w-full px-4 py-2 text-sm font-medium text-white bg-green-600 border border-transparent rounded-md shadow-sm hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500"
          on:click={handleRegister}
        >
          회원가입
        </button>
      {/if}
      {#if !isAuthCodeVisible}
        <button
          class="w-full px-4 py-2 text-sm font-medium text-white bg-indigo-600 border border-transparent rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          on:click={handleAuth}
        >
          인증번호 받기
        </button>
      {/if}
    </div>
  </div>
  