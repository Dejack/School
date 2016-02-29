/// <summary>
/// Author : Manben Chen
/// ID : A00937960
/// Version : 02/08/2016
/// </summary>
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab04_MDI {
    public partial class ImageForm : Form {

        /// <summary>
        /// Child of ParentForm, incharge of displaying images
        /// </summary>

        private Image myImage;
        private String imagePath;

        public ImageForm() {
            InitializeComponent();
            myImage = null;
        }

        /// <summary>
        /// getter and setter for myImage
        /// </summary>
        public Image theImage {
            get {
                return myImage;
            }
            set {
                myImage = value;
                this.AutoScrollMinSize = myImage.Size;
            }
        }

        /// <summary>
        /// getter and setter for imagePath
        /// </summary>
        public String path {
            get {
                return imagePath;
            }
            set {
                imagePath = value;
            }
        }

        /// <summary>
        /// Paints the image onto the form
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ImageForm_Paint(object sender, PaintEventArgs e) {
            if (myImage != null) {
                e.Graphics.DrawImage(myImage, AutoScrollPosition.X, AutoScrollPosition.Y, myImage.Width, myImage.Height);
            }
        }
    }
}
