package com.kiyanitsa.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kiyanitsa.test.model.Merchant
import com.kiyanitsa.test.service.MerchantService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.io.File
import java.io.IOException


@SpringBootApplication
class TestApplication

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
	@Bean
	fun runner(merchantService: MerchantService) {

		// read json and write to db

//			val typeReference: TypeReference<List<Merchant>> = object : TypeReference<List< Merchant?>?>() {}
//			val inputStream = TypeReference::class.java.getResourceAsStream("/json/users.json")
//		val mapper = ObjectMapper()
//		val typeReference: TypeReference<List<Merchant>> = object : TypeReference<List<Merchant?>?>() {}
//		val inputStream = TypeReference::class.java.getResourceAsStream("/json/users.json")
		try {
			val JSON = jacksonObjectMapper()

			val jsonStr = """[
    {
      "goto_cookie_lifetime": 60,
      "rating": "3.3",
      "image": "http://cdn.admitad.com/campaign/images/2017/10/31/7bd130435c2e4c3f6d9688ef10dba1aa.png",
      "currency": "RUB",
      "activation_date": "2017-10-31T17:19:46",
      "id": 18669,
      "connection_status": "active",
      "gotolink": "https://ad.admitad.com/g/k15msx7fve162aae1f4eea7e0adbb8/",
      "site_url": "https://general-food.ru/",
      "regions": [
        {
          "region": "RU"
        }
      ],
      "status": "active",
      "description": "General Food &ndash; фудтех компания, занимающаяся производством и доставкой здорового питания на весь день в Москве.\r\n&nbsp;\r\n\r\nЗарабатывайте вместе с General-food!\r\n\r\nБонусы:&nbsp; Распространяется только на оплаченный заказ нового пользователя!\r\n\r\nПериод: месяц\r\n\r\nВсе вознаграждения касаются только рационов, интернет магазин не подпадает под бонусную программу&nbsp;\r\n\r\nот 10 заказов и более - 300 руб\r\nот 20 заказов и более - 1000 руб\r\nот 50 заказов и более - 5000 руб\r\nот 100 заказов и более - 10 000 руб\r\nот 200 заказов и более - 20 000 руб\r\nот 300 заказов и более - 30 000 руб\r\nот 400 заказов и более - 40 000 руб\r\nот 500 заказов и более - 50 000 руб\r\n\r\nКаждые два дня курьер привозит клиенту еду на следующие два дня. Каждое блюдо упаковано в специальные вакуумные пакеты и контейнеры, что позволяет избежать протекания или потери вкусовых свойств и увеличивает срок хранения. Компания является одним из лидеров рынка по качеству продукта: возврат после первого заказа составляет 90% (в разы превышает возврат клиентов у компаний-конкурентов)\r\n\r\nВозможности клиента:\r\n\r\n\r\n\tзаказать готовый рацион (на 6/12/18/24 дня): меню на каждый день фиксировано\r\n\tсобрать собственный рацион (на 2/6 дней): клиент сам выбирает блюда для каждого приема пищи на каждый день\r\n\tзаказать отдельные блюда в интернет-магазине (минимальная сумма заказа &ndash; 1500₽)\r\n\r\n\r\nЦелевая аудитория бренда:\r\n\r\n\r\n\tжители Москвы (внутри МКАД)\r\n\tдоход от 80.000₽ в месяц\r\n\r\n\r\nТипы целевого клиента:\r\n\r\n\r\n\tнепрофессиональные спортсмены, работающие в сфере интеллектуального труда \r\n\tвладельцы собственного бизнеса и топ-менеджеры небольших компаний\r\n\tменеджеры среднего звена в больших компаниях (Яндекс, Mars, Unilever, Mail.Ru, Sanofi и проч.)\r\n\tжены владельцев бизнеса и топ-менеджеров\r\n\r\n\r\nНемного технической статистики\r\n\r\n&nbsp;\r\n\r\nОпорные поисковые фразы:\r\n\r\n\r\n\tсбалансированное питание\r\n\tздоровое питание с доставкой\r\n\tдоставка правильного питания\r\n\tпитание с доставкой\r\n\tправильное питание с доставкой\r\n\tсбалансированное питание с доставкой\r\n\tготовая еда на неделю с доставкой\r\n\tпитание на неделю с доставкой\r\n\tдиетическое питание с доставкой",
      "modified_date": "2019-03-11T17:05:39"
    }
  ]"""
			val myList: List<Merchant> = JSON.readValue(jsonStr)
			merchantService.saveAll(myList)
		} catch (e: IOException) {
			println("Unable to save users: " + e.message)
		}
	}


}
