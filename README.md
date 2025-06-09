# jdysk

A modern, cross-platform terminal tool to visually display filesystem usage, inspired by [dysk](https://github.com/Canop/dysk) but written in Java using oshi library.

## Features
- Lists all mounted filesystems in a table
- Shows device path, type, total/used/free space, mount point
- Visual usage bar with color (red/green) for used/free space
- Human-readable sizes (e.g., 1.2G, 500M)
- Cross-platform: works on Linux, macOS, and Windows
- No dependencies except Java and JBang

## Usage

### Prerequisites
- [JBang](https://www.jbang.dev/)

### Run directly
```sh
jbang Dysk.java
```

### Or use the alias (if set up)
```sh
jbang dysk@maxandersen/jdysk
```

### Output Example
```
Filesystem           Type     Total        Used         Use               Free         Mount Point         
/dev/sda1            ext4     234.0G       120.0G       ██████░░░░░  51%  114.0G      /                   
/dev/sdb1            xfs      1.8T         499.0G       ███░░░░░░░░  28%  1.3T        /home/dev           
...
```

## How it was made
- **Language:** Java 11+
- **Scriptable:** Uses [JBang](https://www.jbang.dev/) for easy scripting and dependency management
- **Filesystem Info:** Uses [OSHI](https://github.com/oshi/oshi) to get cross-platform filesystem and device information
- **Visuals:** Unicode and ANSI color codes for terminal output
- **Plan-driven:** Implementation followed a step-by-step plan documented in `plan.md`, with notes and progress logs
- **No-Op Logger:** Uses SLF4J no-op logger to suppress unwanted log output from dependencies

## Development Notes
- See `plan.md` for the full planning and progress log
- The script is designed to be portable and easy to run anywhere Java and JBang are available

## License
MIT (same as original dysk) 