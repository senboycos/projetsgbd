import os
import glob
import tkinter
from tkinter import *
from tkinter import ttk
from ttkwidgets import CheckboxTreeview

def liste_cochee():
    print(tree.get_checked())


def populate_tree(tree, node):
    if tree.set(node, "type") != 'directory':
        return

    path = tree.set(node, "fullpath")
    tree.delete(*tree.get_children(node))

    parent = tree.parent(node)
    special_dirs = [] if parent else glob.glob('.') + glob.glob('..')

    for p in special_dirs + os.listdir(path):
        ptype = None
        p = os.path.join(path, p).replace('\\', '/')
        if os.path.isdir(p):
            ptype = "directory"
        elif os.path.isfile(p):
            ptype = "file"

        fname = os.path.split(p)[1]
        id = tree.insert(node, "end", text=fname, values=[p, ptype])

        if ptype == 'directory':
            if fname not in ('.', '..'):
                tree.insert(id, 0, text="dummy")
                tree.item(id, text=fname)
        elif ptype == 'file':
            size = os.stat(p).st_size
            tree.set(id, "size", "%d bytes" % size)


def populate_roots(tree):
    dir = os.path.abspath('.\\Communiques').replace('\\', '/')
    node = tree.insert('', 'end', text="Communiques", values=[dir, "directory"])
    populate_tree(tree, node)


def update_tree(event):
    tree = event.widget
    populate_tree(tree, tree.focus())


def change_dir(event):
    tree = event.widget
    node = tree.focus()
    if tree.parent(node):
        path = os.path.abspath(tree.set(node, "fullpath"))
        if os.path.isdir(path):
            os.chdir(path)
            tree.delete(tree.get_children(''))
            populate_roots(tree)


def autoscroll(sbar, first, last):
    """Hide and show scrollbar as needed."""
    first, last = float(first), float(last)
    if first <= 0 and last >= 1:
        sbar.grid_remove()
    else:
        sbar.grid()
    sbar.set(first, last)


root = tkinter.Tk()


vsb = ttk.Scrollbar(orient="vertical")
hsb = ttk.Scrollbar(orient="horizontal")

tree = CheckboxTreeview(columns=("fullpath", "type", "size"),
                    displaycolumns="size", yscrollcommand=lambda f, l: autoscroll(vsb, f, l),
                    xscrollcommand=lambda f, l: autoscroll(hsb, f, l))


vsb['command'] = tree.yview
hsb['command'] = tree.xview

tree.heading("#0", text="Liste des Fichiers", anchor='w')
tree.heading("size", text="File Size", anchor='w')
tree.column("size", stretch=0, width=100)

populate_roots(tree)
tree.bind('<<TreeviewOpen>>', update_tree)
tree.bind('<Double-Button-1>', change_dir)

# Arrange the tree and its scrollbars in the toplevel
tree.grid(column=0, row=0, sticky='nswe')
vsb.grid(column=1, row=0, sticky='ns')
hsb.grid(column=0, row=1, sticky='ew')
root.grid_columnconfigure(0, weight=1)
root.grid_rowconfigure(0, weight=1)

root.geometry("1080x720")
root.minsize(480, 360)
root.iconbitmap("")
root.configure(background='#FAF3CA')

frame_buttons = Frame(root, bg='#FAF3CA')

download_button = Button(frame_buttons, text="Charger dans la base de donn??e", font=("Montserrat", 25), bg='white', fg='Black', command=print(tree.get_checked()))
download_button.pack()

frame_buttons.grid()

root.mainloop()
