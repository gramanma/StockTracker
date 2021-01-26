# My Plant Diary 

---

Design Document  

Nathan Coffee
Matthew Saling
Max Graman  

## Introduction 

Do you remember when you planted the apple tree?  Do you know when to water and fertilize your plants? MyPlantDiary can help you:  

-	Record dates and locations where you planted plants.
-	Take and view photos of a plant throughout its life.
-	Record when you added water, fertilizer, and other amendments.
-	Be aware of upcoming events for a plant: when to water, when growing season ends, etc.  

Use your Android device to create your own plant diary.  Take photos with the on-device camera.  Create reminders based on what you did in previous years.   Receive alerts about upcoming events for your plant.  

## Storyboard

[Plant Diary Storyboard](https://projects.invisionapp.com/prototype/Plant-Diary-ck0bict0n005bqh01aaeu8tuu/play/c6560121)

![MyPlantDiaryFirstScreen](https://user-images.githubusercontent.com/2224876/82161817-15ee8880-986e-11ea-8cda-f04ad1412893.png)

## Functional Requirements

### Requirement 100.0: Search for Stocks & Crypto

#### Scenario

As a user interested investing in stocks and cryptocurrency, I want to be able to search stocks and crypto based on any part of the name: ticker or company name.  

#### Dependencies

Stock and crypto price data are available and accessible. (Yahoo Finanace API) 

#### Assumptions

Company names are in English.  

Market data is up to date / live.  

#### Examples
1.1  

**Given** a feed of stock/crypto data is available  

**When**  I search for “GME”  

**Then** I should receive at least one result with these attributes:  

Company name: GameStop  
Ticker: GME  
Last Price: $147.98  


1.2  
**Given** a feed of stock/crypto data is available  

**When** I search for “Bitcoin”  

**Then** I should receive at least one result with these attributes:   

Cryptocurrency name: Bitcoin  
Ticker: BTC  
Last Price: $10000
 

1.3  
**Given** a feed of stock/crypto data is available  
**When** I search for “jksdnfgjksndfg93”  
**Then** I should receive zero results  


### Requirement 101: Save Stocks/Crypto Tickers to a List

#### Scenario

As a user interested investing in stocks and cryptocurrency, I want to be able to search stocks and crypto based on any part of the name: ticker or company name to save them to a list of watched stocks.     

#### Dependencies
Stock and crypto price data are available and accessible. (Yahoo Finanace API)  

#### Assumptions  
Company names are in English.  
Market data is up to date / live. 

#### Examples  

1.1  
**Given** a feed of plant data is available  
**Given** Stock/crypto tickers are available  
**When**  

-	Select the stock GameStop  
-   View the historical data
-   Add to a list
**Then**  when I navigate to the Watchlist view, I should see a entry for GameStop with the option to remove it & the last price.  

## Class Diagram

![ClassDiagram](https://user-images.githubusercontent.com/2224876/82162015-54387780-986f-11ea-998f-a45fdf8c3bf1.png)

### Class Diagram Description


**MainActivity:**  The first screen the user sees.  This will have a list of stocks & cryptocurrencies and a news feed.

**TickerDetailsActivity:**  A screen that shows details of a given stock.  

**RetrofitInstance:** Boostrap class required for Retrofit.  

**Ticker:** Noun class that represents a stock/crypto.  

**ITickerDAO:** Interface for Retrofit to find and parse ticker JSON.  

## Scrum Roles

- DevOps/Product Owner/Scrum Master: Matthew Saling
- Frontend Developer: Max Graman  
- Integration Developer: Nathan Coffee  

## Weekly Meeting

Tuesdays at 5 PM.  Use this discord:

Meeting Information
[Discord](https://discord.gg/m8pXX3Ex2G)








