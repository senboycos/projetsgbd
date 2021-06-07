import matplotlib.pyplot as plt
import os
import pandas as pd
import json
import mysql.connector
import geopandas as gpd


def dessinerCarte(jour,mois,annee):
    def afficher(jour, mois, annee):
        mydb = mysql.connector.connect(
            host="localhost",
            user="root",
            database="covidbd"
        )
        mycursor = mydb.cursor()
        query = f"select l.nomLocalite,l.nbCas from localite l inner join communiques c on l.id_communique = c.id where c.dateHeureExtraction = '{jour}/{mois}/{annee}';"
        print(query)
        mycursor.execute(query)
        myresult = mycursor.fetchall()
        mydict = {}
        for x in myresult:
            mydict[f"{x[0]}"] = x[1]
        return mydict

    mydict = afficher(jour,mois,annee)

    file = os.path.join("senegal_administrative", "senegal_administrative.shp")
    cities_file = os.path.join("senegal_administrative", "sn.csv")
    cities = pd.read_csv(cities_file)

    map = gpd.read_file(file)

    ax = map.plot(figsize=(10, 10), alpha=0.5, edgecolor='k')

    def_geo = gpd.GeoDataFrame(cities, geometry=gpd.points_from_xy(cities.lng, cities.lat))
    def_geo.plot(ax=ax, color="red")

    annotations = []
    i = 0
    while i < len(def_geo.city):
        point = def_geo.geometry[i]
        city = def_geo.city[i]
        x = def_geo.lng[i]
        y = def_geo.lat[i]
        cas = mydict.get(city)
        if cas is None:
            cas = 0
        an = ax.annotate(city + " : " + str(cas), xy=(x, y - 0.09))
        annotations.append(an)
        i += 1
    plt.title(f"cas du {jour}/{mois}/{annee}")
    plt.show()

dessinerCarte("18","05","2021")


