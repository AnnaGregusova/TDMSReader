# TDMSReader
This Java library is designed for reading and processing of data stored in Technical Data Management Streaming (TDMS) format. It offers a  solution for applications requiring access to structured binary data. The library parses TDMS files, recognizing and managing its structure comprised of groups and channels, each representing different data segments and types.

## TDMS Format
TDMS files organize data in three main types: Lead-In, Metadata, and Raw Data. The Lead-In data part provides an overview of how the file is organized (Tag, Mask, Version...). Metadata describe detailed information about the data stored, such as number of objects (groups, channels), it also contains properties of both group and channels and much more. Finally, Raw Data is the actual data collected, and it is only stored in the Channels, which are part of Groups within the TDMS file. You can find detailed information about the structure here: https://www.ni.com/en/support/documentation/supplemental/06/the-ni-tdms-file-format.html.

# Getting started
## Instalation
1. Clone the repo
   ```java
      git clone https://github.com/AnnaGregusova/TDMSReader
      cd TDMSReader/src/main/java/cz/cuni/mff/TDMSReader
   ```
2. Compile
   ```java
      javac Main.java
   ```
3. Run it
   ```java
      java Main
   ```
## Usage

### **1. Initializing TDMS File:**
```java
TDMSFile tdmsFile = TDMSFile.read(path);
```
Reads the TDMS file from the specified path and initializes a TDMSFile object.

### **2. Working with File Properties:**
```java
tdmsFile.getProperties();
```
Returns ArrayList of TDMSProperty objects(properties) associated with the TDMS file.

### **3. Accessing Groups:**
```java
ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
```
Returns an ArrayList of TDMSGroup objects of all groups in the file.
```java
TDMSGroup tdmsGroup = tdmsFile.getGroup("Group_name");
```
Returns a specific TDMSgroup by name.

### **4. Group Details and Channels:**
```java
tdmsGroup.getName();
```
Returns the name of the group.

```java
tdmsGroup.getProperties();
```
Returns an ArrayList of properties associated with the group.
```java
tdmsGroup.getChannels();
```
Returns an ArrayList containing all TDMSchannels within the group.

```java
TDMSChannel tdmsChannel = tdmsGroup.getChannel("Channel_name");
```
Obtains a specific channel by name from the group.

### **5. Channel Information and Data:**
```java
tdmsChannel.getName();
```
Returns the name of the channel.
```java
tdmsChannel.getProperties();
```
Returns an ArrayList of properties associated with the channel.

```java
tdmsChannel.getPropertyValue("Property_name");
```
Returns the value of a specified property of the channel.
```java
tdmsChannel.getRawData();
```
Returns all raw data from the channel.
```java
tdmsChannel.getRawData(10);
```
Specify how many Raw data you want to get.

### 6. Iterating Over Groups and Channels:

```java
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

