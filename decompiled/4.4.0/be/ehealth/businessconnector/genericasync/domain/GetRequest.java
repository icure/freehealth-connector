package be.ehealth.businessconnector.genericasync.domain;

import java.util.ArrayList;
import java.util.List;

public class GetRequest {
   private String reference;
   private Integer maxMessages;
   private Integer maxTAcks;
   private List<String> includeIOs;
   private List<String> excludeIOs;
   private List<String> tackMessageNames;

   private GetRequest(Builder.BasicSteps builder) {
      this.reference = builder.reference;
      this.maxMessages = builder.maxMessages;
      this.maxTAcks = builder.maxTAcks;
      this.includeIOs = builder.includeIOs;
      this.excludeIOs = builder.excludeIOs;
      this.tackMessageNames = builder.tackMessageNames;
   }

   public static Builder.GetRequestBuilderStep newBuilder() {
      return new Builder.GetRequestSteps();
   }

   public String getReference() {
      return this.reference;
   }

   public Integer getMaxMessages() {
      return this.maxMessages;
   }

   public Integer getMaxTAcks() {
      return this.maxTAcks;
   }

   public List<String> getIncludeIOs() {
      return this.includeIOs;
   }

   public List<String> getExcludeIOs() {
      return this.excludeIOs;
   }

   public List<String> getTackMessageNames() {
      return this.tackMessageNames;
   }

   public static final class Builder {
      private Builder() {
      }

      static class GetRequestSteps implements GetRequestBuilderStep {
         GetRequestSteps() {
         }

         public BuildStep withReference(String reference) {
            return (new BasicSteps()).withReference(reference);
         }

         public MaxTAcksStep withMaxMessages(Integer maxMessages) {
            return (new BasicSteps()).withMaxMessages(maxMessages);
         }

         public BuildStep withDefaults() {
            return (new BasicSteps()).withDefaults();
         }
      }

      private static class BasicSteps implements DefaultsStep, ReferenceStep, MaxMessagesStep, MaxTAcksStep, IncludeIOsStep, ExcludeIOsStep, TackMessageNamesStep, BuildStep {
         private Integer maxMessages;
         private Integer maxTAcks;
         private String reference;
         private List<String> includeIOs;
         private List<String> excludeIOs;
         private List<String> tackMessageNames;

         private BasicSteps() {
            this.includeIOs = new ArrayList();
            this.excludeIOs = new ArrayList();
         }

         public BuildStep withDefaults() {
            return this;
         }

         public BuildStep withReference(String reference) {
            this.reference = reference;
            return this;
         }

         public MaxTAcksStep withMaxMessages(Integer maxMessages) {
            this.maxMessages = maxMessages;
            return this;
         }

         public IncludeIOsStep withMaxTAcks(Integer maxTAcks) {
            this.maxTAcks = maxTAcks;
            return this;
         }

         public ExcludeIOsStep withIncludeIOs(List<String> includeIOs) {
            this.includeIOs = includeIOs;
            return this;
         }

         public TackMessageNamesStep withExcludeIOs(List<String> excludeIOs) {
            this.excludeIOs = excludeIOs;
            return this;
         }

         public BuildStep withTackMessageNames(List<String> tackMessageNames) {
            this.tackMessageNames = tackMessageNames;
            return this;
         }

         public GetRequest build() {
            return new GetRequest(this);
         }
      }

      public interface BuildStep {
         GetRequest build();
      }

      public interface DefaultsStep {
         BuildStep withDefaults();
      }

      public interface ExcludeIOsStep {
         TackMessageNamesStep withExcludeIOs(List<String> var1);

         GetRequest build();
      }

      public interface GetRequestBuilderStep {
         BuildStep withReference(String var1);

         MaxTAcksStep withMaxMessages(Integer var1);

         BuildStep withDefaults();
      }

      public interface IncludeIOsStep {
         ExcludeIOsStep withIncludeIOs(List<String> var1);

         GetRequest build();
      }

      public interface MaxMessagesStep {
         MaxTAcksStep withMaxMessages(Integer var1);
      }

      public interface MaxTAcksStep {
         IncludeIOsStep withMaxTAcks(Integer var1);

         GetRequest build();
      }

      public interface ReferenceStep {
         BuildStep withReference(String var1);
      }

      public interface TackMessageNamesStep {
         BuildStep withTackMessageNames(List<String> var1);

         GetRequest build();
      }
   }
}
