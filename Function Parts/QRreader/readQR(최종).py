import pyzbar.pyzbar as pyzbar
import numpy as np
import cv2
 
def decode(im) : 
  # Find barcodes and QR codes
  decodedObjects = pyzbar.decode(im)
  # Print results
  for obj in decodedObjects:
    data = obj.data.decode('ascii')
    return data
