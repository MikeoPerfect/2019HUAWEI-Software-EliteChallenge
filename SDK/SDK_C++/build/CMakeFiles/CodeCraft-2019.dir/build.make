# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.5

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/linmiaomiao/桌面/SDK/SDK_C++/CodeCraft-2019

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/linmiaomiao/桌面/SDK/SDK_C++/build

# Include any dependencies generated for this target.
include CMakeFiles/CodeCraft-2019.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/CodeCraft-2019.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/CodeCraft-2019.dir/flags.make

CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o: CMakeFiles/CodeCraft-2019.dir/flags.make
CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o: /home/linmiaomiao/桌面/SDK/SDK_C++/CodeCraft-2019/CodeCraft-2019.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/linmiaomiao/桌面/SDK/SDK_C++/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o -c /home/linmiaomiao/桌面/SDK/SDK_C++/CodeCraft-2019/CodeCraft-2019.cpp

CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/linmiaomiao/桌面/SDK/SDK_C++/CodeCraft-2019/CodeCraft-2019.cpp > CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.i

CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/linmiaomiao/桌面/SDK/SDK_C++/CodeCraft-2019/CodeCraft-2019.cpp -o CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.s

CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o.requires:

.PHONY : CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o.requires

CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o.provides: CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o.requires
	$(MAKE) -f CMakeFiles/CodeCraft-2019.dir/build.make CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o.provides.build
.PHONY : CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o.provides

CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o.provides.build: CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o


# Object files for target CodeCraft-2019
CodeCraft__2019_OBJECTS = \
"CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o"

# External object files for target CodeCraft-2019
CodeCraft__2019_EXTERNAL_OBJECTS =

/home/linmiaomiao/桌面/SDK/SDK_C++/bin/CodeCraft-2019: CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o
/home/linmiaomiao/桌面/SDK/SDK_C++/bin/CodeCraft-2019: CMakeFiles/CodeCraft-2019.dir/build.make
/home/linmiaomiao/桌面/SDK/SDK_C++/bin/CodeCraft-2019: CMakeFiles/CodeCraft-2019.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/linmiaomiao/桌面/SDK/SDK_C++/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable /home/linmiaomiao/桌面/SDK/SDK_C++/bin/CodeCraft-2019"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/CodeCraft-2019.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/CodeCraft-2019.dir/build: /home/linmiaomiao/桌面/SDK/SDK_C++/bin/CodeCraft-2019

.PHONY : CMakeFiles/CodeCraft-2019.dir/build

CMakeFiles/CodeCraft-2019.dir/requires: CMakeFiles/CodeCraft-2019.dir/CodeCraft-2019.cpp.o.requires

.PHONY : CMakeFiles/CodeCraft-2019.dir/requires

CMakeFiles/CodeCraft-2019.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/CodeCraft-2019.dir/cmake_clean.cmake
.PHONY : CMakeFiles/CodeCraft-2019.dir/clean

CMakeFiles/CodeCraft-2019.dir/depend:
	cd /home/linmiaomiao/桌面/SDK/SDK_C++/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/linmiaomiao/桌面/SDK/SDK_C++/CodeCraft-2019 /home/linmiaomiao/桌面/SDK/SDK_C++/CodeCraft-2019 /home/linmiaomiao/桌面/SDK/SDK_C++/build /home/linmiaomiao/桌面/SDK/SDK_C++/build /home/linmiaomiao/桌面/SDK/SDK_C++/build/CMakeFiles/CodeCraft-2019.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/CodeCraft-2019.dir/depend

