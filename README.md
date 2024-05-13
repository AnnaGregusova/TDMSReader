# TDMSReader
This Java library is designed for reading and processing of data stored in Technical Data Management Streaming (TDMS) format. It offers a  solution for applications requiring access to structured binary data. The library parses TDMS files, recognizing and managing its structure comprised of groups and channels, each representing different data segments and types.
# Getting started
## Instalation
1. Clone the repo
   ```ruby
      git clone https://github.com/AnnaGregusova/TDMSReader
      cd TDMSReader/src/main/java/cz/cuni/mff/TDMSReader
   ```
2. Compile
   ```ruby
      javac Main.java
   ```
3. Run it
   ```ruby
      java Main
   ```
## Usage

### **1. Initializing TDMS File:**

TDMSFile tdmsFile = TDMSFile.read(path); - Reads the TDMS file from the specified path and initializes a TDMSFile object.

**2. Working with File Properties:**

tdmsFile.getProperties(); - Retrieves a list of properties associated with the TDMS file.

**3. Accessing Groups:**

ArrayList<TDMSGroup> groups = tdmsFile.getGroups(); - Returns an ArrayList of TDMSGroup objects representing all groups in the file.
TDMSGroup tdmsGroup = tdmsFile.getGroup("Group_name"); - Retrieves a specific group by name.

**4. Group Details and Channels:**

tdmsGroup.getName(); - Returns the name of the group.
tdmsGroup.getProperties(); - Fetches a list of properties associated with the group.
tdmsGroup.getChannels(); - Returns an ArrayList containing all channels within the group.
TDMSChannel tdmsChannel = tdmsGroup.getChannel("Channel_name"); - Obtains a specific channel by name from the group.

**5. Channel Information and Data:**

tdmsChannel.getName(); - Provides the name of the channel.
tdmsChannel.getProperties(); - Returns a list of properties associated with the channel.
tdmsChannel.getPropertyValue(); - Gets the value of a specified property of the channel.
tdmsChannel.getRawData(); - Retrieves raw data from the channel.
