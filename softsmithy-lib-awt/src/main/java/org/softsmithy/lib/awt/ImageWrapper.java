/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * ImageWrapper.java
 *
 * Created on 12. Februar 2003, 14:31
 */

package org.softsmithy.lib.awt;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 *
 * @author  puce
 */
class ImageWrapper extends Image {
  
  /** Holds value of property image. */
  private final Image image;
  
  /** Creates a new instance of ImageWrapper */
  public ImageWrapper(Image image) {
    this.image = image;
  }
  
  /** Determines the width of the image. If the width is not yet known,
   * this method returns <code>-1</code> and the specified
   * <code>ImageObserver</code> object is notified later.
   * @param     observer   an object waiting for the image to be loaded.
   * @return    the width of this image, or <code>-1</code>
   *                   if the width is not yet known.
   * @see       java.awt.Image#getHeight
   * @see       java.awt.image.ImageObserver
   *
   */
  @Override
  public int getWidth(ImageObserver observer) {
    return image.getWidth(observer);
  }
  
  /** Creates a scaled version of this image.
   * A new <code>Image</code> object is returned which will render
   * the image at the specified <code>width</code> and
   * <code>height</code> by default.  The new <code>Image</code> object
   * may be loaded asynchronously even if the original source image
   * has already been loaded completely.  If either the <code>width</code>
   * or <code>height</code> is a negative number then a value is
   * substituted to maintain the aspect ratio of the original image
   * dimensions.
   * @param width the width to which to scale the image.
   * @param height the height to which to scale the image.
   * @param hints flags to indicate the type of algorithm to use
   * for image resampling.
   * @return     a scaled version of the image.
   * @see        java.awt.Image#SCALE_DEFAULT
   * @see        java.awt.Image#SCALE_FAST
   * @see        java.awt.Image#SCALE_SMOOTH
   * @see        java.awt.Image#SCALE_REPLICATE
   * @see        java.awt.Image#SCALE_AREA_AVERAGING
   * @since      JDK1.1
   *
   */
  @Override
  public Image getScaledInstance(int width, int height, int hints) {
    return new ImageWrapper(image.getScaledInstance(width, height, hints));
  }
  
  /** Flushes all resources being used by this Image object.  This
   * includes any pixel data that is being cached for rendering to
   * the screen as well as any system resources that are being used
   * to store data or pixels for the image.  The image is reset to
   * a state similar to when it was first created so that if it is
   * again rendered, the image data will have to be recreated or
   * fetched again from its source.
   * <p>
   * This method always leaves the image in a state such that it can
   * be reconstructed.  This means the method applies only to cached
   * or other secondary representations of images such as those that
   * have been generated from an <tt>ImageProducer</tt> (read from a
   * file, for example). It does nothing for off-screen images that
   * have only one copy of their data.
   *
   */
  @Override
  public void flush() {
    image.flush();
  }
  
  /** Creates a graphics context for drawing to an off-screen image.
   * This method can only be called for off-screen images.
   * @return  a graphics context to draw to the off-screen image.
   * @see     java.awt.Graphics
   * @see     java.awt.Component#createImage(int, int)
   *
   */
  @Override
  public Graphics getGraphics() {
    return image.getGraphics();
  }
  
  /** Gets the object that produces the pixels for the image.
   * This method is called by the image filtering classes and by
   * methods that perform image conversion and scaling.
   * @return     the image producer that produces the pixels
   *                                  for this image.
   * @see        java.awt.image.ImageProducer
   *
   */
  @Override
  public ImageProducer getSource() {
    return image.getSource();
  }
  
  /** Determines the height of the image. If the height is not yet known,
   * this method returns <code>-1</code> and the specified
   * <code>ImageObserver</code> object is notified later.
   * @param     observer   an object waiting for the image to be loaded.
   * @return    the height of this image, or <code>-1</code>
   *                   if the height is not yet known.
   * @see       java.awt.Image#getWidth
   * @see       java.awt.image.ImageObserver
   *
   */
  @Override
  public int getHeight(ImageObserver observer) {
    return image.getHeight(observer);
  }
  
  /** Gets a property of this image by name.
   * <p>
   * Individual property names are defined by the various image
   * formats. If a property is not defined for a particular image, this
   * method returns the <code>UndefinedProperty</code> object.
   * <p>
   * If the properties for this image are not yet known, this method
   * returns <code>null</code>, and the <code>ImageObserver</code>
   * object is notified later.
   * <p>
   * The property name <code>"comment"</code> should be used to store
   * an optional comment which can be presented to the application as a
   * description of the image, its source, or its author.
   * @param       name   a property name.
   * @param       observer   an object waiting for this image to be loaded.
   * @return      the value of the named property.
   * @see         java.awt.image.ImageObserver
   * @see         java.awt.Image#UndefinedProperty
   *
   */
  @Override
  public Object getProperty(String name, ImageObserver observer) {
    return image.getProperty(name, observer);
  }
  
  /** Getter for property image.
   * @return Value of property image.
   *
   */
  protected Image getImage() {
    return this.image;
  }
  
}
