# Instagram Image Filer

Author: Jugal Joshi



This document explains how to use the various models with the scope of the Application.

THe submitted files contains 4 main interfaces, which have varios implementains that can be called depending ont he requirements.

Interfaces 
1) GenerateImage.java

	Methods:
	a) getImage(): returns an image in the Buffrent image format after it has been generated.

	Implementaitons:
	a) Rainbow.java: Generates a rainbow image with horizontal stripes. 
	This class takes width and height as the initializing parameters, 
	after which the getImage method should be called to obtain the generated image.

	b) RainbowVertical.java: Generates a rainbow image with vertical stripes. 
	This class takes width and height as the initializing parameters, 
	after which the getImage method should be called to obtain the generated image.

	c) FlagOfFrance.java: Generates a the flag France of image with horizontal stripes. 
	This class takes width and height as the initializing parameters, 
	after which the getImage method should be called to obtain the generated image.

	d) FlagOfGreece.java: Generates a the flag Greece of image. 
	This class takes width and height as the initializing parameters, 
	after which the getImage method should be called to obtain the generated image.

	e) FlagOfGreece.java: Generates a the flag Greece of image. 
	This class takes width and height as the initializing parameters, 
	after which the getImage method should be called to obtain the generated image.
	
	f) CheckerBoard.java: Generates a Chekerboard image. 
	This class takes one side of a square within the board as the initializing parameter, 
	after which the getImage method should be called to obtain the generated image.
	
	g) FlagOfSwitzerland.java: Generates a Flag OfSwitzerland image. 
	This class takes one side of a square within the board as the initializing parameter, 
	after which the getImage method should be called to obtain the generated image.

2) Filter.java

	Methods:
	a)blur: returns the blurred image in BufferedImageFormat.

	b)sharpen: returns the sharpened image in BufferedImageFormat.

	c)greyscale: returns the greyscale image in BufferedImageFormat.

	d)sepia: returns the sepia image in BufferedImageFormat.

	e)dither: returns the dithered image in BufferedImageFormat.

	f)mosaic: returns the mosaic image in BufferedImageFormat.

	Implementaions:
	a)FilerImpl.java: this expects a bufferedimage as the imput, after which the varios interfacemethods can be used.

3)ImageReader

	Methods:
	a)getImage: returns the readImage.

	Implementaions:
	a)ImageReaderImpl.java: takes a file path as the input, after whicht the image can be retieved.

4)ImageWiter

	Methods:
	a)witerImage: withers the image
		paramentes:
		i)image: the image to be written
		ii)writePath: where the image is to be written
		iii)imageSaveType: the format the image must be saved in.

	Implementaions:
	a)ImageReaderImpl.java: takes a file path as the input, after whicht the image can be retieved.



Other than than these classes and interface there are 3 additional files
1) SupportedImageFormats.java (enum): tho store the suppoorted file formats
2) Main.java: the driver for this model
3) ModelTests.java: a testing class for some basic tests.





Citations:
one image of New york city has been obtained from the following link
https://unsplash.com/photos/wh-7GeXxItI
unsplash provies fair use pictures that anyone can access

rest of the images provided are either images that pefromed operations on this image or were generated by the code.
we authoriz the use of these imaages withing the scope of this assigment and anyfuture assigment that depends on this one.


Additions:
Added 2 abstract classes in the createimages package named:
1) AbstractSquares: to generate a sqare pattern, used this to astract certain common functions of the Swiss flag and checkerboard.
2) AbstarctStripes: to abstract certain functions of all the flags and rainbow images;

these 2 classes were simply used for abstracting, code was simply moved to these classes without any changes from the rainbow or checkerboard class.

Modifications:
1) added 2 methods to the Filter interface to accomodate dithering and mosaicing.
2) implemented the 2 methods in FilterImpl
2) added method clamp() to clamp rgb values between the range 0-255.


Controller Design:
The controller is designed with Command Pattern Design. The flow of the controller goes this way: 

1.) The controller reads the input file passed from the main's args[0],and based on the input from it acts accordingly. 

	The input.txt script file syntax:

	load -> Will prompt user to load the file by asking them to provide the file loaction.

	generate <Flag/pattern> -> the command generate followed by the name of the flag or pattern will generate the flag/pattern.

	<name of the filter> -> By simply entering the name of the filter will apply the filter as many times as the user writes it until the save command is entered by the user. 
	
	save -> the save command will save the image with applied filter. The name of the file would be <NameOfFile> -<Filter>. <FileType>. For example if the user has applied blur, sepia, dither filter on the file name nyc.png, the generated file name would be nyc-blur-sepia-dither.png. 

2.) After determing the the type of command the control will act accordingly: 

	load -> The controller will ask the user for file loaction and will call the ImageReaderImpl class to extract the image from the image path. 
	
	generate -> The controller would call the respective GenerateCommand class to generate the images. For eg: whengenerating rainbow, it will make a new object of  RainbowGenerateCommand and then pass the object into commandController class and then commandController object will call execute() and then the image would be generated.
	
	filter -> The controller will apply the filter with the help of newly created Filter image. Eg: image=filter.greyscale()
	
	save ->  Upon save command, the controller would create WriteImageCommand  object and pass it to commandController class and then commandController object will call execute() and then the image would be saved in the file system.
	
	
	



