# StockTracker

---

Design Document  

Nathan Coffee  
Matthew Saling  
Max Graman  
## Introduction 

Have you ever needed to track a stock on the go?  Are you sick of needed to drive to the nearest Bloomberg terminal to see stock prices? StockTracker can help you:  

-	Add your favorite stocks and cryptocurrencies to a list.
-	View the latest price of stocks and cryptocurrencies.
-	Read news about stocks and cryptocurrencies. 

Use your Android device to create your watchlist and personalized news feed.  Take the last prices of your investments on the go.  

## Storyboard

[StockTrader Storyboard](https://www.fluidui.com/editor/live/project/p_cTrwk2yPhweCZmQo8kZMIkh41C5ESBpG)

![StockTraderFirstScreen](https://user-images.githubusercontent.com/2224876/82161817-15ee8880-986e-11ea-8cda-f04ad1412893.png)

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

- Product Owner/Scrum Master: Matthew Saling
- DevOps/Frontend Developer: Max Graman  
- Integration Developer: Nathan Coffee  

## Weekly Meeting

Tuesdays at 5 PM.  Use this discord:

Meeting Information
[Discord](https://discord.gg/m8pXX3Ex2G)








