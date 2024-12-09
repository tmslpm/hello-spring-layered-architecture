# Hello Spring Layered Architecture

## Project Structure

### project root

<pre><code>
root
|- buildSrc
|  \- ... (💡<a href="https://docs.gradle.org/current/userguide/writing_plugins.html#pre_compiled_script_plugin">Pre-compiled Script Plugin</a>)
|
|- gradle
|  |- wrapper/...
|  \- libs.versions.toml (💡<a href="https://docs.gradle.org/current/userguide/version_catalogs.html">Version Catalogs</a>)
|
|- src (💡<a href="https://spring.io/">Spring Application</a>)
|   |--- app
|   |--- bll
|   |--- dal
|   \--- pl
|
\- ...
</code></pre>


- `App`:
  The "**app**" subproject is the main entry point for your Spring Boot applications.
  All other subprojects inherit the dependencies of this project.
- `BLL`:
  The "**bll**" subproject is the "**B**usiness **L**ogic **L**ayer" of your application.
  It typically contains classes that implement the business logic, including business rules, data processing algorithms,
  and more.
- `DAL`:
  The "**dal**" subproject is the "**D**ata **A**ccess **L**ayer" of your application.
  It is responsible for data access, including database interactions and communication
  with other data sources. It usually contains classes for performing data read/write
  operations, executing database queries, managing connections, and handling related tasks.
- `PL`
    The "**pl**" subproject is the "**P**resentation **L**ayer" of your application, where
    the user interface (UI) is typically implemented. This layer may include classes for UI
    components, user input handling, data display, and other related functionalities.

### project logic

```txt
   ┌───────────────────────────┐
   │                           │
   │           A P P           │
   │                           │
   │      ┌─────────────┐      │
   │      │             │      │ 
   │      │     P L     │      │ 
   │      │             │      │ 
   │      └─┬─────────▲─┘      │ 
   │        │         │        │ 
   │      ┌─▼─────────┴─┐      │ 
   │      │             │      │ 
   │      │     BLL     │      │ 
   │      │             │      │ 
   │      └─┬─────────▲─┘      │ 
   │        │         │        │ 
   │      ┌─▼─────────┴─┐      │ 
   │      │             │      │ 
   │      │     DAL     │      │ 
   │      │             │      │ 
   │      └─────────────┘      │ 
   │                           │  
   └───────────────────────────┘              
```




## Project Dependency Management

The project use the feature Gradle's **Versions Catalog** feature to manage its
dependencies in a centralized and efficient way.

### Centralized Dependency Declaration

In this project, the **`app`** subproject is responsible for declaring the dependencies.
It serves as the main entry point of the application and contains all the essential
dependencies needed for the project to run. These dependencies are declared within the
**`dependencies`** block of the `build.gradle` file in the **`app`** subproject.

### Inheritance of Dependencies

The other subprojects, namely **`dal`** (Data Access Layer), **`bll`** (Business Logic Layer),
and **`pl`** (Presentation Layer), inherit the dependencies declared in the **`app`** subproject.
By configuring the dependencies in the root **`app`** subproject, all other subprojects can
access these dependencies without having to explicitly declare them.

Each subproject can inherit the necessary dependencies.

### Dependency Management Flow

- **`app`** subproject:
  The central hub for all dependency declarations.
- **`dal`, `bll`, `pl`** subprojects:
  These layers inherit the dependencies from the **`app`** subproject, ensuring
  consistency across the entire application.

By using the Versions Catalog feature, this project achieves a clean and manageable structure,
where dependencies are easily maintained and updated in one place.

For the versions catalog, see the `libs.versions.toml` file located in the `./gradle/libs.versions.toml`
directory.

This file centralizes the versions of dependencies used across the project, helping to maintain
consistency and avoid version conflicts.