from tkinter import *
import os
window = Tk()


def callDownload():
    os.system("javac ../../out/production/projetsgbd/main")



window.title("Covid19")

window.geometry("1080x720")
window.minsize(480, 360)
window.iconbitmap("")
window.configure(background='#FAF3CA')

frame = Frame(window, bg='#FAF3CA')
frame_buttons = Frame(window, bg='#FAF3CA')

label_title = Label(frame, text="Data Aqcuisition", font=("Montserrat", 40), bg='#FAF3CA', fg="Black")
label_title.pack()

download_button = Button(frame_buttons, text="Telecharger le fichier du jour", font=("Montserrat", 25), bg='white', fg='Black', command=callDownload)
download_button.pack()
insert_button = Button(frame_buttons, text="Mettre à jour les fichiers", font=("Montserrat", 25), bg='white', fg='Black',)
insert_button.pack()

frame.pack()
frame_buttons.pack()

window.mainloop()

