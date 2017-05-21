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
Alkoholfrei darf sich ein Bier nennen, wenn es einen Alkoholgehalt von 0,5% und weniger enthält. Mittlerweile werden auch alkoholfreie Biere mit 0,0 Prozent angeboten.

Alt
Obergäriges dunkelbernsteinfarbenes Vollbier mit etwa 4,8% Alkohol, das hauptsächlich in Düsseldorf und am Niederrhein getrunken wird. Der Name stammt von der alten obergärigen Brauart. Alt wird aus kleinen Gläsern (0,2 l) getrunken und kann, je nach Rezept, hopfen-bitter bis malzig-süß sein.

Berliner Weisse
Ein obergäriges, hefetrübes und lange gelagertes Bier, das häuptsächlich in und um Berlin bekannt ist. Der Geschmack der Berliner Weisse ist säuerlich, erfrischend vor allem im Sommer und wird oft in Pokalen mit einem Schuß Himbeer- oder Waldmeister-Sirup serviert. Die Berliner Weisse hat ca. 2,8% Alkohol und wird aus Gersten und Weizenmalz gebraut.

Bockbier
Das Bockbier ist ein vollmundiges Starkbier mit rund 7% Alkohol. Im Gegensatz zu anderen Bieren enthält Bockbier zusätzlich noch Hefe, um den Stammwürzegehalt zu erhöhen. Im Norden wird eher hell gebrautes Bockbier getrunken, im süddeutschen Raum bevorzugt man die dunkleren Bockbiere. Bockbiere, wie der Maibock und der Doppelbock, werden zu besonderen Anlässen gebraut.

Broyhan-Bier
Cord Broyhan braute im Jahre 1526 in Hannover als erster dieses nach ihm bennannte Broyhan-Bier. Es enthält als Gewürz nur Hopfen und helles Malz. Das hellbraune, obergärige Bier wurde zu einem Exportschlager der Stadt Hannover.

Dampfbier
Obergärige nur leicht gehopfte Gerstenbiere, die mit Hilfe von Dampfmaschinen gebraut wurden, nannte man Dampfbier. Den Namen erhielt es auch von den Brauern, weil die Gärung vor allem an der Oberfläche stattfindet und die zerplatzenden Kohlensäurebläschen der Decke wie Dampf aussehen.

Dinkelbier
Bei dieser Sorte wird Dinkelmalz anstelle von Gerstenmalz verwendet. Das Dinkelbier ist eine obergärige Biersorte. Der Alkoholgehalt von Dinkelbier liegt bei etwa 4,5 %.

Diätpils
Bei der Herstellung von Diätpils werden in einem besonderen Brauverfahren fast sämtliche Kohlehydrate zu Alkohol umgewandelt. Das Bier ist kalorienarm, jedoch sehr alkoholhaltig. Zielgruppe des Diätpils sind Diabetiker.

Doppelbock
Doppelbock ist ein Bockbier, welches mit einem Stammwürzegehalt von über 18 % eingebraut wird. Der Alkoholgehalt beträgt zwischen 5 und 12 %. Diese haben meist die Endsilbe "-ator" im Namen. Doppelbock wurde normalerweise nur inder Zeit um die Fastenzeit herum gebraut und ausgeschenkt.

Eisbier
Ober- oder untergäriges Bier, das extrem kalt gelagert wird. Die Charakteristik ist sehr weich, sehr mild und schlank mit dezenter Blume.

Eisbock
Bockbier mit rund 7% Alkoholgehalt, welchem durch Gefrieren Wasser entzogen wurde. Damit kann ein deutlich höherer Alkoholgehalt erreicht werden.

Export
Untergäriges Bier mit malzigem Geschmack und schwächer gehopft als Pilsener Biere. Es enthält 5 bis 5,5 % Alkohol.

Gose
Gose ist ein obergäriges, helles Weissbier. Stammt ursprünglich aus Goslar am Harz. Während früher das Gose Bier durch eine Spontangärung entstanden ist, wird heute nur noch die obergärige Brauart zur Bierherstellung verwendet. Neben der alkoholischen findet auch eine bakterielle Milchsäuregärung statt, die zu dem typischen säuerlichen Geschmack führt.

Kölsch
Kölsch ist ein helles obergäriges Vollbier mit ca. 4,8% Alkohol, das nur in einem bestimmten Umfeld um die Stadt Köln herum gebraut werden darf. Es wird aus 0,2l-Gläsern, sogenannten Stangen getrunken.

Kräusen
Kräusen nennt man den Schaum, der sich nach Zugabe der Hefe an der Oberfläche bildet. Kräusenbier ist ungefiltert und damit hefetrüb.

Lagerbier
Untergäriges, blankes Vollbier von hellgelber Farbe mit 4,6 bis 5,6% Alkohol. In Deutschland werden Biere so bezeichnt, die unter 12,5% Stammwürze haben und nicht der stark gehopften Pilsener Brauart angehören. Das Verbreitungsgebiet liegt meist in Baden-Württemberg, Bayern, Schweiz und Österreich. Im englischen Sprachraum ist das Lagerbier mehr verbreitet.

Leichtbier
Kalorienarmes Bier, bei dem der Alkohol- und Energiegehalt vierzig Prozent niedriger als beim Pils liegt. Während der Gärung wird entweder die Bildung von Alkohol gebremst oder der Alkohol nach dem Gärprozeß entzogen.

Märzenbier
Ein Vollbier mit 4-5% Alkohol. Das Märzen kommt vom alten Brauch, das untergärige Bier zum Winterende (März) zu Brauen, damit man es bis in den Spätsommer lagern kann. Der milde malzige Geschmack rührt von der Verwendung eines Spezialmalzes her. Die klassische Variante des Märzen ist goldgelb, jedoch gibt es auch dunkles Märzen. Der Begriff wird heutzutage vor allem in Süddeutschland und Österreich für etwas stärkere Lagerbiere verwendet.

Malzbier/Malztrunk
Das dunkle Malzbier ist mit 0,1 bis 0,4% fast alkoholfrei. Strenggenommen ist Malzbier eigentlich gar kein Bier, da es seinen süßlichen Geschmack durch Beigabe von Invertzucker und Zuckerkulör erhält. Malzgetränke sind Vollbiere, die obergärig mit einer Stammwürze von meist elf bis zwölf Prozent gebraut werden.

Mumme
Die Entstehungsgeschichte des je nach Brauart schwach bis stark alkoholhaltigen Bier aus Braunschweig reicht bis in das Spätmittelalter zurück. Früher war das Bier ein sehr wichtiger Exportschlager bis in die weite Welt. In der heutigen Zeit war bis 2008 die Braunschweiger Mumme nur in Dosen als alkoholfreie Version erhältlich. Nun wird zum ersten Mal seit etwa 200 Jahren wieder eine alkoholhaltige Variante produziert.

Pilsator
Eine ostdeutsche Bierspezialität, die weniger herb als Pilsner Bier wie auch weniger malzig als Export ist.

Pilsener
Pilsbier ist die wohl bekannteste und am weitesten verbreitete Biersorte. Erstmals gebraut wurde es im Jahre 1842 unter maßgeblicher Beteiligung des bayrischen Braumeisters Josef Groll im böhmischen Pilsen. Pilsener Biere sind untergärige Vollbiere. Hopfenbetont, schlank und spritzig im Geschmack mit feinem Schaum wird es aus Tulpen oder Pokalen getrunken. Es hat einen Alkoholgehalt von knapp 5 %. Nach Pilsner Brauart hergestellte Biere bilden heute den Großteil der in Deutschland produzierten und verkauften Biere.

Rauchbier:
Bei dieser Bamberger Spezialität wird bei der Herstellung das Malz über einem Holzfeuer getrocknet. Dieses Verfahren verleiht der Bier einen rauchigen Geschmack.

Roggen
Bei der Herstellung wird Roggenmalz anstelle von Gerstenmalz verwendet. Roggenbier ist ein obergäriges Bier. Es wird meist von kleinen Hausbrauereinen in der Pfalz und Bayern hergestellt.

Schwarzbier
Das Schwarzbier ist ein spritziges, untergäriges Vollbier mit ca. 4,8 bis 5% Alkohol. Geschmacklich sind Schwarzbiere uneinheitlich. Seine dunkle Farbe erhält Schwarzbier meist durch die Verwendung dunklen Braumalzes oder Röstmalzes. Der Stammwürzegehalt beträgt mindestens 11 %.

Seefahrtsbier
Zur sogenannten Schiffermahlzeit am zweiten Freitag des Februar wird nur noch dieses Bier im Bremer Ratskeller gebraut. Bei dem Festessen wird es zu Bremer Braunkohl mit Pinkel getrunken. Der Hauptinhaltsstoff des Getränkes ist Malz.

Spezialbier
Die Spezialbiere sind meist Festbiere, die für bestimmte Anlässe, wie Münchner Oktoberfest und Cannstatter Wasen, gebraut werden.

Steinbier
Natursteine werden für das Steinbier über dem offenen Feuer erhitzt und in die Maische getaucht. Der Malzzucker karamelisiert auf den Steinen, die erst wieder zur Nachgärung zugegeben werden. Das Bier hat einen rauchigen Geschmack und ist besonders in Baden-Württemberg und Franken verbreitet.

Weizenbier
Weizenbier, das in Südbayern und Österreich auch Weißbier genannt wird, ist ein obergäriges Bier tund muss mindestens zur Hälfte aus Weizenmalz hergestellt sein. Man unterscheidet zwischen Hefeweizen, das nicht gefiltert wird, und Kristallweizen, welches gefiltert und somit von den Hefepartikeln befreit wird. Der Alkoholanteil beträgt bei beiden etwa 5 bis 5,5 %. Der Ausschank erfolgt traditionell in besonders hohen und schlanken Gläsern.

Weizenbock
Ein obergäriges Bockbier, welches unter Verwendung von Weizenmalz gebraut wird. Je nach Verwendung des Malzes, erhält man dunklen oder hellen Weizenbock.
**/