<script>
  import { Route } from 'tinro';
  import Main from './pages/Main.svelte';
  import Admin from './pages/Admin.svelte';
  import Accommodation from './pages/Accommodation.svelte';
    import Trip from './pages/Trip.svelte';
</script>

<Route path="/"><Main /></Route>
<Route path="/admin"><Admin /></Route>
<Route path="/accommodations"><Accommodation /></Route>
<Route path="/trips"><Trip /></Route>
