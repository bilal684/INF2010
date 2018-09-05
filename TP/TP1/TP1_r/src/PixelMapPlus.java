import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < width; col++)
			{
				imageData[row][col] = imageData[row][col].Negative();
			}
		}
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		PixelMap tmp = this.toBWImage();
		this.imageType = tmp.imageType;
		this.imageData = tmp.imageData;	
		tmp.clearData();
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		PixelMap tmp = this.toGrayImage();
		this.imageType = tmp.imageType;
		this.imageData = tmp.imageData;	
		tmp.clearData();	
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		PixelMap tmp = this.toColorImage();
		this.imageType = tmp.imageType;
		this.imageData = tmp.imageData;
		tmp.clearData();		
	}
	
	public void convertToTransparentImage()
	{
		PixelMap tmp = this.toTransparentImage();
		this.imageType = tmp.imageType;
		this.imageData = tmp.imageData;	
		tmp.clearData();

	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		AbstractPixel[][] oldImage = imageData;
		ImageType oldType = this.getType();
		this.clearData();
		this.AllocateMemory(oldType, height , width);

		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < width; col++)
			{
				
				double posX = Math.cos(angleRadian) * col - Math.sin(angleRadian) * row - Math.cos(angleRadian) * x + Math.sin(angleRadian) * y + x;
				double posY = Math.sin(angleRadian) * col + Math.cos(angleRadian) * row - Math.sin(angleRadian) * x - Math.cos(angleRadian) * y + y;
				if((int) posY < height && (int) posX < width && (int) posY >= 0 && (int) posX >= 0)
				{
					imageData[row][col] = oldImage[(int) posY][(int) posX];
				}
			}
		}
		oldImage = null;
		
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		AbstractPixel[][] oldImage = imageData;
		ImageType oldType = this.getType();
		double ratioH = (double) h / (double) height;
		double ratioW = (double) w / (double) width;

		this.clearData();
		this.AllocateMemory(oldType, h , w);
		
		for(int row = 0; row < h; row++)
		{
			for(int col = 0; col < w; col++)
			{
				imageData[row][col] = oldImage[(int) (row/ratioH)][(int) (col/ratioW)];
			}
		}
		oldImage = null;
	}
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		if(this.imageType != pm.getType() )
			pm = new PixelMap(this.imageType, pm);
		if(row0 < 0 || col0 < 0 || col0 > this.getWidth() || row0 > this.getHeight() || (row0 + pm.getHeight()) > height || (col0 + pm.getWidth()) > width)
			throw new IllegalArgumentException();
		for(int row = row0; row < (row0 + pm.getHeight()); row++)
		{
			for(int col = col0; col < (col0 + pm.getWidth()); col++)
			{
				imageData[row][col] = pm.imageData[row - row0][col - col0];
			}
		}		
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w) throws IllegalArgumentException
	{	
		if(w <= 0 || h <= 0)
			throw new IllegalArgumentException();
		
		AbstractPixel[][] oldImage = imageData;
		int oldHeight = height;
		int oldWidth = width;
		AllocateMemory(imageType, h, w);
		
		for(int row = 0; row < Math.min(h, oldHeight); row++)
		{
			for(int col = 0; col < Math.min(w, oldWidth); col++)
			{
				imageData[row][col] = oldImage[row][col];
			}
		}
		oldImage = null;
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{	
		AbstractPixel[][] oldImage = imageData;
		
		AllocateMemory(imageType, height, width);
		
		for(int row = Math.max(0, rowOffset); row < Math.min(height, height + rowOffset); row++)
		{
			for(int col = Math.max(0, colOffset); col < Math.min(width, width + colOffset); col++)
			{
				imageData[row][col] = oldImage[row - rowOffset][col - colOffset];
			}
		}
		oldImage = null;
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		int oldHeight = height;
		int oldWidth = width;
		translate((int) ((double)-y/zoomFactor), (int) ((double)-x/zoomFactor));
		crop((int) ((double)height/zoomFactor), (int) ((double)width/zoomFactor));
		resize(oldWidth, oldHeight);
		height = oldHeight;
		width = oldWidth;
	}
}
