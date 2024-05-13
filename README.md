# TDMSReader
This Java library is designed for reading and processing of data stored in Technical Data Management Streaming (TDMS) format. It offers a  solution for applications requiring access to structured binary data. The library parses TDMS files, recognizing and managing its structure comprised of groups and channels, each representing different data segments and types.

##TDMS Format
TDMS files organize data in three main types: Lead-In, Metadata, and Raw Data. The Lead-In part provides an overview of how the file is organized, making it easier to navigate through the data. Metadata offers detailed information about the data stored, like descriptions and settings, which helps understand what the data represents. Finally, Raw Data is the actual data collected, and it is only stored in the Channels, which are part of Groups within the TDMS file. You can find detailed information here https://www.ni.com/en/support/documentation/supplemental/06/the-ni-tdms-file-format.html.


The TDMS format is a file format for the storage of measurement data and metadata
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
```ruby
TDMSFile tdmsFile = TDMSFile.read(path);
```
Reads the TDMS file from the specified path and initializes a TDMSFile object.

### **2. Working with File Properties:**
```ruby
tdmsFile.getProperties();
```
Returns ArrayList of TDMSProperty objects(properties) associated with the TDMS file.

### **3. Accessing Groups:**
```ruby
ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
```
Returns an ArrayList of TDMSGroup objects of all groups in the file.
```ruby
TDMSGroup tdmsGroup = tdmsFile.getGroup("Group_name");
```
Returns a specific TDMSgroup by name.

### **4. Group Details and Channels:**
```ruby
tdmsGroup.getName();
```
Returns the name of the group.

```ruby
tdmsGroup.getProperties();
```
Returns an ArrayList of properties associated with the group.
```ruby
tdmsGroup.getChannels();
```
Returns an ArrayList containing all TDMSchannels within the group.

```ruby
TDMSChannel tdmsChannel = tdmsGroup.getChannel("Channel_name");
```
Obtains a specific channel by name from the group.

### **5. Channel Information and Data:**
```ruby
tdmsChannel.getName();
```
Returns the name of the channel.
```ruby
tdmsChannel.getProperties();
```
Returns an ArrayList of properties associated with the channel.
```ruby
tdmsChannel.getPropertyValue();
```
Returns the value of a specified property of the channel.
```ruby
tdmsChannel.getRawData();
```
Returns all raw data from the channel.
```ruby
tdmsChannel.getRawData(10);
```
Specify how many Raw data you want to get.

### 6. Iterating Over Groups and Channels:

```ruby
for (TDMSGroup group : groups) {
    ArrayList<TDMSChannel> channels = group.getChannels();
    for (TDMSChannel channel : channels) {
        ArrayList<Object> rawData = channel.getRawData(1);
        System.out.println(channel.getName());
        System.out.println(channel.getProperties());
        System.out.println(rawData);
    }
}
```
This is how you can iterate through all groups and their channels, accessing names, properties and raw data of each channel:

