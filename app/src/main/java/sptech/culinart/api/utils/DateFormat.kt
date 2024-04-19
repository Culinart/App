package sptech.culinart.api.utils
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

data class DateFormat(
    val diaDaSemanaAbreviado: String,
    val numeroDoDia: Int,
    val nomeDoMesAbreviado: String
)


fun converterDataParaFormatoDescritivo(data: LocalDate): DateFormat {
    // Converte a string para LocalDate


    // Obtém o nome abreviado do dia da semana
    val diaDaSemanaAbreviado = data.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("pt", "BR"))

    // Obtém o número do dia e o nome abreviado do mês (3 primeiras letras)
    val numeroDoDia = data.dayOfMonth
    val nomeDoMesAbreviado = data.month.getDisplayName(TextStyle.SHORT, Locale("pt", "BR"))

    // Retorna um objeto DataDescritiva com os dados obtidos
    return DateFormat(diaDaSemanaAbreviado, numeroDoDia, nomeDoMesAbreviado)
}