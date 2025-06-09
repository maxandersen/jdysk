# Port dysk to java

## Plan

1. **Research dysk Features**
   - Review dysk's output and features (columns, visual usage bar, filtering, sorting, output formats).
   - Identify which features are feasible and most valuable for a Java port.

2. **Set Up Java Project with JBang**
   - Initialize a new Java project using JBang for easy scripting and distribution.
   - Ensure the project can be run as a single-file script or with minimal setup.

3. **Gather Filesystem Information**
   - Use Java's `java.nio.file.FileSystems` and `java.nio.file.FileStore` APIs to enumerate mounted filesystems.
   - Collect relevant data: filesystem name, type, total size, used space, free space, mount point, and disk type (if possible).

4. **Calculate Usage Metrics**
   - Compute used percentage and other metrics for each filesystem.
   - Handle edge cases (e.g., removable drives, unmounted filesystems).

5. **Terminal Output Formatting**
   - Design a table layout similar to dysk, with columns for each metric.
   - Implement a visual usage bar using Unicode block characters and ANSI colors for terminal output.
   - Ensure alignment and readability across different terminal widths.

6. **Add Sorting and Filtering (Optional)**
   - Allow sorting by usage, size, or mount point via command-line arguments.
   - Implement filtering to show only certain filesystems (e.g., local, removable).

7. **Testing and Cross-Platform Checks**
   - Test on macOS, Linux, and Windows (as feasible) to ensure compatibility.
   - Validate output against `df` and dysk for accuracy.

8. **Documentation and Packaging**
   - Write usage instructions and examples in the README.
   - Package the script for easy use with JBang.

## Implementation

### Implementation Notes

#### 1. Project Setup
- No existing Java or JBang files found in the workspace. Will start with a new JBang-compatible Java file (e.g., Dysk.java).
- Will use Java 11+ for better filesystem API support and terminal features.

#### 2. Research dysk Features
- Key features to replicate:
  - Table output with columns: filesystem, type, disk, used, use%, free, size, mount point
  - Visual usage bar in terminal (using Unicode/ANSI)
  - Detection of disk type (SSD, removable, etc.) if possible
  - Sorting/filtering (optional, for later)
- Reference: [dysk GitHub](https://github.com/Canop/dysk)

#### Next Steps
- Create `Dysk.java` as a JBang script.
- Implement filesystem enumeration and basic table output.

---

#### Progress Log
- Created `Dysk.java` as a JBang-compatible script.
- It enumerates filesystems using Java's FileSystems and FileStore APIs.
- Prints a table with columns: Filesystem, Type, Total, Used, Free, Mount Point (human-readable sizes).
- Next: Add a visual usage bar and improve disk type detection if possible.
- Implemented a visual usage bar using Unicode block characters and ANSI colors, shown as a new column ('Use') between 'Used' and 'Free' in the table output.
- Switched OSHI dependency to com.github.oshi:oshi-core-java11:6.8.0 for better compatibility with JBang and Java 11+.
- Noted that OSHI's getName() returns a label, not the device path. Updated the Filesystem column to use getDevice() for accurate device path output (e.g., /dev/sda1).
- Next: Investigate disk type detection (SSD, removable, etc.) using Java APIs or heuristics.