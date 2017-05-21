package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="brand")
public class Brand {
	@ManyToOne private Beer beer;
	@Id @Column(name="brewing_method")	private String brewing_method;
	
	public Brand(String method) {
		this.brewing_method = method;
	}
	
	public Brand() {
		this.brewing_method = "Alkoholfreies";
	}
	
	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	public String getBrewingMethod() {
		return brewing_method;
	}

	public void setBrewingMethod(String brewingMethod) {
		if(brewingMethod.matches("\\D+") == false) {
			throw new IllegalArgumentException();
		}
		this.brewing_method = brewingMethod;
	}
	
}

/***
Biersorten:
Alkoholfreies Bier
Alkoholfrei darf sich ein Bier nennen, wenn es einen Alkoholgehalt von 0,5% und weniger enth�lt. Mittlerweile werden auch alkoholfreie Biere mit 0,0 Prozent angeboten.

Alt
Oberg�riges dunkelbernsteinfarbenes Vollbier mit etwa 4,8% Alkohol, das haupts�chlich in D�sseldorf und am Niederrhein getrunken wird. Der Name stammt von der alten oberg�rigen Brauart. Alt wird aus kleinen Gl�sern (0,2 l) getrunken und kann, je nach Rezept, hopfen-bitter bis malzig-s�� sein.

Berliner Weisse
Ein oberg�riges, hefetr�bes und lange gelagertes Bier, das h�upts�chlich in und um Berlin bekannt ist. Der Geschmack der Berliner Weisse ist s�uerlich, erfrischend vor allem im Sommer und wird oft in Pokalen mit einem Schu� Himbeer- oder Waldmeister-Sirup serviert. Die Berliner Weisse hat ca. 2,8% Alkohol und wird aus Gersten und Weizenmalz gebraut.

Bockbier
Das Bockbier ist ein vollmundiges Starkbier mit rund 7% Alkohol. Im Gegensatz zu anderen Bieren enth�lt Bockbier zus�tzlich noch Hefe, um den Stammw�rzegehalt zu erh�hen. Im Norden wird eher hell gebrautes Bockbier getrunken, im s�ddeutschen Raum bevorzugt man die dunkleren Bockbiere. Bockbiere, wie der Maibock und der Doppelbock, werden zu besonderen Anl�ssen gebraut.

Broyhan-Bier
Cord Broyhan braute im Jahre 1526 in Hannover als erster dieses nach ihm bennannte Broyhan-Bier. Es enth�lt als Gew�rz nur Hopfen und helles Malz. Das hellbraune, oberg�rige Bier wurde zu einem Exportschlager der Stadt Hannover.

Dampfbier
Oberg�rige nur leicht gehopfte Gerstenbiere, die mit Hilfe von Dampfmaschinen gebraut wurden, nannte man Dampfbier. Den Namen erhielt es auch von den Brauern, weil die G�rung vor allem an der Oberfl�che stattfindet und die zerplatzenden Kohlens�urebl�schen der Decke wie Dampf aussehen.

Dinkelbier
Bei dieser Sorte wird Dinkelmalz anstelle von Gerstenmalz verwendet. Das Dinkelbier ist eine oberg�rige Biersorte. Der Alkoholgehalt von Dinkelbier liegt bei etwa 4,5 %.

Di�tpils
Bei der Herstellung von Di�tpils werden in einem besonderen Brauverfahren fast s�mtliche Kohlehydrate zu Alkohol umgewandelt. Das Bier ist kalorienarm, jedoch sehr alkoholhaltig. Zielgruppe des Di�tpils sind Diabetiker.

Doppelbock
Doppelbock ist ein Bockbier, welches mit einem Stammw�rzegehalt von �ber 18 % eingebraut wird. Der Alkoholgehalt betr�gt zwischen 5 und 12 %. Diese haben meist die Endsilbe "-ator" im Namen. Doppelbock wurde normalerweise nur inder Zeit um die Fastenzeit herum gebraut und ausgeschenkt.

Eisbier
Ober- oder unterg�riges Bier, das extrem kalt gelagert wird. Die Charakteristik ist sehr weich, sehr mild und schlank mit dezenter Blume.

Eisbock
Bockbier mit rund 7% Alkoholgehalt, welchem durch Gefrieren Wasser entzogen wurde. Damit kann ein deutlich h�herer Alkoholgehalt erreicht werden.

Export
Unterg�riges Bier mit malzigem Geschmack und schw�cher gehopft als Pilsener Biere. Es enth�lt 5 bis 5,5 % Alkohol.

Gose
Gose ist ein oberg�riges, helles Weissbier. Stammt urspr�nglich aus Goslar am Harz. W�hrend fr�her das Gose Bier durch eine Spontang�rung entstanden ist, wird heute nur noch die oberg�rige Brauart zur Bierherstellung verwendet. Neben der alkoholischen findet auch eine bakterielle Milchs�ureg�rung statt, die zu dem typischen s�uerlichen Geschmack f�hrt.

K�lsch
K�lsch ist ein helles oberg�riges Vollbier mit ca. 4,8% Alkohol, das nur in einem bestimmten Umfeld um die Stadt K�ln herum gebraut werden darf. Es wird aus 0,2l-Gl�sern, sogenannten Stangen getrunken.

Kr�usen
Kr�usen nennt man den Schaum, der sich nach Zugabe der Hefe an der Oberfl�che bildet. Kr�usenbier ist ungefiltert und damit hefetr�b.

Lagerbier
Unterg�riges, blankes Vollbier von hellgelber Farbe mit 4,6 bis 5,6% Alkohol. In Deutschland werden Biere so bezeichnt, die unter 12,5% Stammw�rze haben und nicht der stark gehopften Pilsener Brauart angeh�ren. Das Verbreitungsgebiet liegt meist in Baden-W�rttemberg, Bayern, Schweiz und �sterreich. Im englischen Sprachraum ist das Lagerbier mehr verbreitet.

Leichtbier
Kalorienarmes Bier, bei dem der Alkohol- und Energiegehalt vierzig Prozent niedriger als beim Pils liegt. W�hrend der G�rung wird entweder die Bildung von Alkohol gebremst oder der Alkohol nach dem G�rproze� entzogen.

M�rzenbier
Ein Vollbier mit 4-5% Alkohol. Das M�rzen kommt vom alten Brauch, das unterg�rige Bier zum Winterende (M�rz) zu Brauen, damit man es bis in den Sp�tsommer lagern kann. Der milde malzige Geschmack r�hrt von der Verwendung eines Spezialmalzes her. Die klassische Variante des M�rzen ist goldgelb, jedoch gibt es auch dunkles M�rzen. Der Begriff wird heutzutage vor allem in S�ddeutschland und �sterreich f�r etwas st�rkere Lagerbiere verwendet.

Malzbier/Malztrunk
Das dunkle Malzbier ist mit 0,1 bis 0,4% fast alkoholfrei. Strenggenommen ist Malzbier eigentlich gar kein Bier, da es seinen s��lichen Geschmack durch Beigabe von Invertzucker und Zuckerkul�r erh�lt. Malzgetr�nke sind Vollbiere, die oberg�rig mit einer Stammw�rze von meist elf bis zw�lf Prozent gebraut werden.

Mumme
Die Entstehungsgeschichte des je nach Brauart schwach bis stark alkoholhaltigen Bier aus Braunschweig reicht bis in das Sp�tmittelalter zur�ck. Fr�her war das Bier ein sehr wichtiger Exportschlager bis in die weite Welt. In der heutigen Zeit war bis 2008 die Braunschweiger Mumme nur in Dosen als alkoholfreie Version erh�ltlich. Nun wird zum ersten Mal seit etwa 200 Jahren wieder eine alkoholhaltige Variante produziert.

Pilsator
Eine ostdeutsche Bierspezialit�t, die weniger herb als Pilsner Bier wie auch weniger malzig als Export ist.

Pilsener
Pilsbier ist die wohl bekannteste und am weitesten verbreitete Biersorte. Erstmals gebraut wurde es im Jahre 1842 unter ma�geblicher Beteiligung des bayrischen Braumeisters Josef Groll im b�hmischen Pilsen. Pilsener Biere sind unterg�rige Vollbiere. Hopfenbetont, schlank und spritzig im Geschmack mit feinem Schaum wird es aus Tulpen oder Pokalen getrunken. Es hat einen Alkoholgehalt von knapp 5 %. Nach Pilsner Brauart hergestellte Biere bilden heute den Gro�teil der in Deutschland produzierten und verkauften Biere.

Rauchbier:
Bei dieser Bamberger Spezialit�t wird bei der Herstellung das Malz �ber einem Holzfeuer getrocknet. Dieses Verfahren verleiht der Bier einen rauchigen Geschmack.

Roggen
Bei der Herstellung wird Roggenmalz anstelle von Gerstenmalz verwendet. Roggenbier ist ein oberg�riges Bier. Es wird meist von kleinen Hausbrauereinen in der Pfalz und Bayern hergestellt.

Schwarzbier
Das Schwarzbier ist ein spritziges, unterg�riges Vollbier mit ca. 4,8 bis 5% Alkohol. Geschmacklich sind Schwarzbiere uneinheitlich. Seine dunkle Farbe erh�lt Schwarzbier meist durch die Verwendung dunklen Braumalzes oder R�stmalzes. Der Stammw�rzegehalt betr�gt mindestens 11 %.

Seefahrtsbier
Zur sogenannten Schiffermahlzeit am zweiten Freitag des Februar wird nur noch dieses Bier im Bremer Ratskeller gebraut. Bei dem Festessen wird es zu Bremer Braunkohl mit Pinkel getrunken. Der Hauptinhaltsstoff des Getr�nkes ist Malz.

Spezialbier
Die Spezialbiere sind meist Festbiere, die f�r bestimmte Anl�sse, wie M�nchner Oktoberfest und Cannstatter Wasen, gebraut werden.

Steinbier
Natursteine werden f�r das Steinbier �ber dem offenen Feuer erhitzt und in die Maische getaucht. Der Malzzucker karamelisiert auf den Steinen, die erst wieder zur Nachg�rung zugegeben werden. Das Bier hat einen rauchigen Geschmack und ist besonders in Baden-W�rttemberg und Franken verbreitet.

Weizenbier
Weizenbier, das in S�dbayern und �sterreich auch Wei�bier genannt wird, ist ein oberg�riges Bier tund muss mindestens zur H�lfte aus Weizenmalz hergestellt sein. Man unterscheidet zwischen Hefeweizen, das nicht gefiltert wird, und Kristallweizen, welches gefiltert und somit von den Hefepartikeln befreit wird. Der Alkoholanteil betr�gt bei beiden etwa 5 bis 5,5 %. Der Ausschank erfolgt traditionell in besonders hohen und schlanken Gl�sern.

Weizenbock
Ein oberg�riges Bockbier, welches unter Verwendung von Weizenmalz gebraut wird. Je nach Verwendung des Malzes, erh�lt man dunklen oder hellen Weizenbock.
**/