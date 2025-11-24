# Gestor de Clientes

Aplicación Android desarrollada en **Kotlin** para gestionar clientes de forma local. Permite agregar, editar, eliminar y buscar clientes utilizando **SQLite** para persistencia de datos. La interfaz incluye un RecyclerView moderno, contador de clientes y búsqueda en tiempo real.

---

## Funcionalidades

- **Pantalla principal**
  - Lista de clientes en un **RecyclerView** (nombre, email, teléfono)
  - Contador de clientes
  - **FloatingActionButton** para agregar nuevos clientes
  - Búsqueda en tiempo real por nombre o email
  - Botones **Editar** y **Eliminar** en cada cliente

- **Formulario de cliente**
  - Campos obligatorios: nombre, email y teléfono
  - Validación de formato de email
  - Validación de teléfono (mínimo 9 dígitos)
  - Inserta o actualiza cliente en la base de datos

- **Persistencia**
  - Guardado de clientes en **SQLite** usando `SQLiteOpenHelper`
  - Carga automática de clientes al iniciar la aplicación

---

## Capturas de pantalla

**Pantalla principal con lista:**

Página principal con lista de clientes, con botones para editar, eliminar y la búsqueda.

![Pantalla principal](capturas/PantallaPrincipal.png)

**Formulario de cliente agregar y busqueda:**

Al darle al botón **+** nos saldrá esta pagina donde rellenaremos los datos para crear un cliente

![Agregar](capturas/CreacionCliente.png)

Una vez creado nuestro cliente lo buscamos para ver que se ha creado correctamente

![Busqueda](capturas/BusquedaCliente.png)

**Formulario editar y borrar:**

Al pulsar el botón de editar, nos saldra esta página donde podemos cambiar los datos. 

![Editar](capturas/EditarCliente.png)

Una vez cambiados los datos, revisamos que se han cambiado los datos

![Editado](capturas/ClienteEditado.png)

Al pulsar el botón de eliminar nos sale el mensaje de confirmacion

![Borrar](capturas/BorrarCliente.png)

Comprobamos que se ha borrado nuestro cliente

![Borrado](capturas/ClienteBorrado.png)

---

## Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/Jaimotas/Gestor-de-Clientes.git
