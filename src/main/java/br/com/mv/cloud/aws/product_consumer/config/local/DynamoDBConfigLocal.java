package br.com.mv.cloud.aws.product_consumer.config.local;

import br.com.mv.cloud.aws.product_consumer.repository.ProductEventLogRepository;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("local")
@EnableDynamoDBRepositories(basePackageClasses = ProductEventLogRepository.class)
public class DynamoDBConfigLocal {

    private final AmazonDynamoDB amazonDynamoDB;

    public DynamoDBConfigLocal() {
        this.amazonDynamoDB = AmazonDynamoDBClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration("http://localhost:4566", Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        com.amazonaws.services.dynamodbv2.document.DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        List<AttributeDefinition> attributeDefinitionList = new ArrayList<AttributeDefinition>();
        attributeDefinitionList.add(new AttributeDefinition().withAttributeName("pk").withAttributeType(ScalarAttributeType.S));
        attributeDefinitionList.add(new AttributeDefinition().withAttributeName("sk").withAttributeType(ScalarAttributeType.S));

        List<KeySchemaElement> keySchemaElements = new ArrayList<KeySchemaElement>();
        keySchemaElements.add(new KeySchemaElement().withAttributeName("pk").withKeyType(KeyType.HASH));
        keySchemaElements.add(new KeySchemaElement().withAttributeName("sk").withKeyType(KeyType.RANGE));

        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName("product-events")
                .withAttributeDefinitions(attributeDefinitionList)
                .withKeySchema(keySchemaElements)
                .withBillingMode(BillingMode.PROVISIONED)
                .withProvisionedThroughput(new ProvisionedThroughput(4L, 4L));

        try {
            Table table = dynamoDB.createTable(createTableRequest);
            table.waitForActive();
        } catch (ResourceInUseException e) {
            System.out.println("Tabela já em uso: " + e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,
                                         DynamoDBMapperConfig config) {
        return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        return this.amazonDynamoDB;
    }
}
