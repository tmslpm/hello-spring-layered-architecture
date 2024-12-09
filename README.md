# Hello Spring Layered Architecture

- run

````
gradlew :src:app:bootRun
````

- build

````
gradlew :src:app:bootJar
````
> output build: `./src/app/build/libs/{rootProject.name}-app-{version}.jar`

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

The project uses Gradle's **Versions Catalog** functionality
to manage its dependency versions centrally and efficiently.

This ensures consistency and avoids version conflicts.

For the versions catalog file, see the `libs.versions.toml` file located in the 
`./gradle/libs.versions.toml` directory. 