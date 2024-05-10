from nptdms import TdmsFile
import sys
a = 3
b = "ahoj"

path4 = "TDMSFiles\\Test_OK_Sample_20210916_125133 (3).tdms"
path6 = "/Users/annagregusova/java/TDMS_library/TDMS_files/NH3_concentration_1a_0002.tdms"
path8 = "TDMSFiles\\test_TDMS_20240404_103108_0009.tdms"
path9 = "TDMSFiles\\09_Point_T60C_V16000mV_20240315_102139.tdms"
path11 ="TDMSFiles\\test_TDMS_20240404_103108_0009.tdms"

def runPath11():
    tdms_file = TdmsFile.read(path11)
    print ( "TDMs File: " + str(tdms_file))
    #print(TdmsFile.read_metadata(path6))
    #print(TdmsFile.data_chunks())

    all_groups = tdms_file.groups()


    print(all_groups)
    group = tdms_file["AI channels"]
    print("Group name: " + group.name)
    all_group_channels = group.channels()
    print("All group channels: ")
    print(all_group_channels)
    channel = group["-AI-Presure_Inlet_01"]
    print("Channel path: " + channel.path)
    channelLength = len(channel)

    print("Channel length " + str(channelLength))
    all_channel_data = channel[:15]
    print("ALl channel data: ")
    for name, value in tdms_file.properties.items():
        print("{0}: {1}".format(name, value))

    print("Raw data")
    for group in tdms_file.groups():
        for channel in group.channels():
            print(f"Channel: {channel.name}")
            data = channel[:15]
            print(f"Data: {data[:]}")

    property_value = tdms_file["AI channels"]["-AI-Presure_Inlet_01"].properties["wf_start_time"]
    print("property value: ")
    print(property_value)
    print("All groups: ")
    print(all_groups)
    print("Group name: " + group.name)
    print("All group channels: ")
    print(all_group_channels)
    print("channel name: " + channel.name)
    print("All channel data")
    print(all_channel_data)


def runPath9():
    tdms_file = TdmsFile.read(path9)
    print ( "TDMs File: " + str(tdms_file))
    #print(TdmsFile.read_metadata(path6))
    #print(TdmsFile.data_chunks())

    all_groups = tdms_file.groups()


    print(all_groups)
    group = tdms_file["SENT channels"]
    print("Group name: " + group.name)
    all_group_channels = group.channels()
    print("All group channels: ")
    print(all_group_channels)
    channel = group["Sample1_temperature"]
    print("Channel path: " + channel.path)
    channelLength = len(channel)

    print("Channel length " + str(channelLength))
    all_channel_data = channel[:15]
    print("ALl channel data: ")
    for name, value in tdms_file.properties.items():
        print("{0}: {1}".format(name, value))

    print("Raw data")
    for group in tdms_file.groups():
        for channel in group.channels():
            print(f"Channel: {channel.name}")
            data = channel[:15]
            print(f"Data: {data[:]}")
    property_value = tdms_file["SENT channels"]["Sample1_temperature"].properties["wf_start_time"]
    print("property value: ")
    print(property_value)
    print("All groups: ")
    print(all_groups)
    print("Group name: " + group.name)
    print("All group channels: ")
    print(all_group_channels)
    print("channel name: " + channel.name)
    print("All channel data")
    print(all_channel_data)

def runPath8():

    tdms_file = TdmsFile.read(path8)
    print ( "TDMs File: " + str(tdms_file))
    #print(TdmsFile.read_metadata(path6))
    #print(TdmsFile.data_chunks())

    all_groups = tdms_file.groups()


    print(all_groups)
    group = tdms_file["AI channels"]
    print("Group name: " + group.name)
    all_group_channels = group.channels()
    print("All group channels: ")
    print(all_group_channels)
    channel = group["-AI-Presure_Inlet_01"]
    print("Channel path: " + channel.path)
    channelLength = len(channel)

    print("Channel length " + str(channelLength))
    all_channel_data = channel[:15]
    print("ALl channel data: ")
    for name, value in tdms_file.properties.items():
        print("{0}: {1}".format(name, value))

    print("Raw data")
    for group in tdms_file.groups():
        for channel in group.channels():
            print(f"Channel: {channel.name}")
            data = channel[:15]
            print(f"Data: {data[:]}")

    property_value = tdms_file["AI channels"]["-AI-Presure_Inlet_01"].properties["wf_start_time"]
    print("property value: ")
    print(property_value)
    print("All groups: ")
    print(all_groups)
    print("Group name: " + group.name)
    print("All group channels: ")
    print(all_group_channels)
    print("channel name: " + channel.name)
    print("All channel data")
    print(all_channel_data)


def runPath6():

    with open(path6, 'rb') as f:
         offset = 1000  
         f.seek(offset)  
         bytes_read = f.read(2000) 
         hex_bytes = bytes_read.hex()   
         print(hex_bytes)


    tdms_file = TdmsFile.read(path6)
    print ( "TDMs File: " + str(tdms_file))
    #print(TdmsFile.read_metadata(path6))
    #print(TdmsFile.data_chunks())

    all_groups = tdms_file.groups()



    group = tdms_file["AI channels"]
    print("Group name: " + group.name)
    all_group_channels = group.channels()
    print("All group channels: ")
    print(all_group_channels)
    channel = group["-AI-NH3_concentration"]
    print("Channel path: " + channel.path)
    channelLength = len(channel)

    print("Channel length " + str(channelLength))
    all_channel_data = channel[:]
    print("ALl channel data: ")
    for name, value in tdms_file.properties.items():
        print("{0}: {1}".format(name, value))

    print("Raw data")
    for group in tdms_file.groups():
        for channel in group.channels():
            print(f"Channel: {channel.name}")
            data = channel[:]
            print(f"Data: {data[:]}")

    property_value = tdms_file["AI channels"]["-AI-NH3_concentration"].properties["wf_start_time"]
    print("property value: ")
    print(property_value)
    print("All groups: ")
    print(all_groups)
    print("Group name: " + group.name)
    print("All group channels: ")
    print(all_group_channels)
    print("channel name: " + channel.name)
    print("All channel data")
    print(all_channel_data)



"""with open(path4, 'rb') as f:
         offset = 0  
         f.seek(offset)  
         bytes_read = f.read() 
         hex_bytes = bytes_read.hex()   
         print(hex_bytes)
         #1656 + 2575 = 4231
         #20600 : 2575 = 8 (8 channelu)"""

def runPath4():
   
    tdms_file = TdmsFile.read(path4)
   
    print("TDMs File: " + str(tdms_file))
    all_groups = tdms_file.groups()
    print(all_groups)

    group = tdms_file["SENT channels"]
    print("Group name: " + group.name)
    all_group_channels = group.channels()
    print("All group channels: ")
    print(all_group_channels)

    channel = group["Test_OK_Sample_pressure"]
    print("Channel path: " + channel.path)
    channelLength = len(channel)

    print("Channel length " + str(channelLength))
    all_channel_data = channel[:30]
    print("ALl channel data: ")
    print(all_channel_data)
    for name, value in tdms_file.properties.items():
        print("{0}: {1}".format(name, value))

    channel2 = group["Test_OK_Sample_invPressureMSN"]
    print("Channel path: " + channel2.path)
    channelLength = len(channel2)
    total_size = 0
    print("Raw data")
    
    for group in tdms_file.groups():
        for channel in group.channels():
            print(f"Channel: {channel.name}")
            data = channel[:]
            size_in_bytes = sys.getsizeof(channel[:])
            print(f"Size of data in bytes: {size_in_bytes}")
            
            print(f"Data: {data[:]}")
          
            print(len(data)//16)
            total_size += len(data)
            print("Size of raw data")
            print(total_size)
      
    print("Channel length " + str(channelLength))
    all_channel_data = channel2[:10]
    print("ALl channel data: ")
    print(all_channel_data)
    for name, value in tdms_file.properties.items():
        print("{0}: {1}".format(name, value))


    property_value = tdms_file["SENT channels"]["Test_OK_Sample_invPressureMSN"].properties["NI_ChannelName"]
    print("property value: ")
    print(property_value)
    print("Length of raw data")
    print(total_size)


#runPath11()
#runPath9()
#runPath8()
#runPath6()
runPath4()
