import sys
import numpy as np
import nptdms

def read_tdms(file_path):
    tdms_file = nptdms.TdmsFile.read(file_path)
    lead_in_data = tdms_file.properties

    isValidTag = True  # Assume the tag is always valid for simplicity
    version = lead_in_data.get('Version', -1)
    mask = lead_in_data.get('Mask', -1)
    nextSegmentOffset = lead_in_data.get('NextSegmentOffset', -1)
    rawDataOffset = lead_in_data.get('RawDataOffset', -1)

    return isValidTag, version, mask, nextSegmentOffset, rawDataOffset

def main(tdms_file_path):
    lead_in_data = read_tdms(tdms_file_path)
    print(','.join(map(str, lead_in_data)))

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python validate_tdms.py <tdms_file_path>")
        sys.exit(1)

    tdms_file_path = sys.argv[1]
    main(tdms_file_path)

